package in.khofid.academy.ui.reader.list;

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

public class ModuleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final MyAdapterClickListener listener;
    private List<ModuleEntity> modules = new ArrayList<>();

    public ModuleListAdapter(MyAdapterClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list_custom, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModuleEntity module = modules.get(position);
        ModuleViewHolder moduleViewHolder = (ModuleViewHolder) holder;
        moduleViewHolder.bind(module.getTitle());
        moduleViewHolder.itemView.setOnClickListener(v -> {
            listener.onItemClicked(holder.getAdapterPosition(), modules.get(moduleViewHolder.getAdapterPosition()).getModuleId());
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public void setModules(List<ModuleEntity> modules) {
        if(modules == null) return;
        this.modules.clear();
        this.modules.addAll(modules);
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {

        final TextView textTitle, textLastSeen;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);
            textLastSeen = itemView.findViewById(R.id.text_last_seen);
        }

        void bind(String title) {
            textTitle.setText(title);
        }
    }

}

interface MyAdapterClickListener {
    void onItemClicked(int position, String moduleId);
}