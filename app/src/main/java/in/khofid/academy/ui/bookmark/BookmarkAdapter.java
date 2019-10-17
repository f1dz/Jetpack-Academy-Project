package in.khofid.academy.ui.bookmark;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import in.khofid.academy.R;
import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.ui.detail.DetailCourseActivity;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private final Activity activity;
    private final BookmarkFragmentCallback callback;

    private List<CourseEntity> courses = new ArrayList<>();

    public BookmarkAdapter(Activity activity, BookmarkFragmentCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public void setListCourses(List<CourseEntity> courses) {
        if(courses == null) return;
        this.courses.clear();
        this.courses.addAll(courses);
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        CourseEntity course = courses.get(position);

        holder.tvTitle.setText(course.getTitle());
        holder.tvDescription.setText(course.getDescription());
        holder.tvDate.setText(String.format("Deadline %s", course.getDeadline()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailCourseActivity.class);
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.getCourseId());
            activity.startActivity(intent);
        });
        holder.imgShare.setOnClickListener(v -> callback.onShareClick(courses.get(holder.getAdapterPosition())));

        Glide.with(holder.itemView.getContext())
                .load(course.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder{

        final TextView tvTitle, tvDescription, tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;

        public BookmarkViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
