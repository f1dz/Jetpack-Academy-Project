package in.khofid.academy.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.khofid.academy.R;
import in.khofid.academy.data.ModuleEntity;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder> {

    private List<ModuleEntity> mModules = new ArrayList<>();

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        holder.bind(mModules.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public void setModules(List<ModuleEntity> modules) {
        if(modules == null) return;
        mModules.clear();
        mModules.addAll(modules);
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder{

        final TextView textTitle;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);
        }

        void bind(String title){
            textTitle.setText(title);
        }
    }
}
