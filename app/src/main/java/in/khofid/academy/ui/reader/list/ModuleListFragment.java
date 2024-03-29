package in.khofid.academy.ui.reader.list;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.khofid.academy.R;
import in.khofid.academy.data.ModuleEntity;
import in.khofid.academy.ui.reader.CourseReaderActivity;
import in.khofid.academy.ui.reader.CourseReaderCallback;
import in.khofid.academy.ui.reader.CourseReaderViewModel;
import in.khofid.academy.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleListFragment extends Fragment implements MyAdapterClickListener{

    public static final String TAG = ModuleListFragment.class.getSimpleName();
    private ModuleListAdapter adapter;
    private CourseReaderCallback courseReaderCallback;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CourseReaderViewModel viewModel;

    public ModuleListFragment() {
        // Required empty public constructor
    }

    public static ModuleListFragment newInstance() {
        return new ModuleListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_module);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            viewModel = obtainViewModel(getActivity());
            adapter = new ModuleListAdapter(this);
            viewModel.getModules().observe(this, moduleEntities -> {
                if(moduleEntities != null) {
                    progressBar.setVisibility(View.GONE);
                    populateRecyclerView(moduleEntities);
                }
            });
        }
    }

    private CourseReaderViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel.class);
    }

    private void populateRecyclerView(List<ModuleEntity> modules) {
        progressBar.setVisibility(View.GONE);
        adapter.setModules(modules);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        courseReaderCallback = ((CourseReaderActivity) context);
    }

    @Override
    public void onItemClicked(int position, String moduleId) {
        courseReaderCallback.moveTo(position, moduleId);
        viewModel.setSelectedModule(moduleId);
    }


}
