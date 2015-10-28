package project.com.isly.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.com.isly.R;
import project.com.isly.adapter.RecyclerAdapter;
import project.com.isly.adapter.StudentsAdapter;
import project.com.isly.helpers.DBH;
import project.com.isly.models.Student;

/**
 * Created by juan on 13/10/15.
 */
public class Statistics extends Fragment {
    ListView list_students;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statistics,container,false);

        list_students=(ListView)v.findViewById(R.id.list_students);
        list_students.setAdapter(new StudentsAdapter(getActivity().getApplicationContext(), DBH.getActiveStudents(getActivity().getApplicationContext())));

        return v;
    }
}
