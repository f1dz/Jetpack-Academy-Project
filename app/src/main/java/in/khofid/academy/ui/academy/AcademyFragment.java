package in.khofid.academy.ui.academy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.khofid.academy.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcademyFragment extends Fragment {


    public AcademyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false);
    }

}
