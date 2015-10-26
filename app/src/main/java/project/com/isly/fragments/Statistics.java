package project.com.isly.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.com.isly.R;
import project.com.isly.adapter.RecyclerAdapter;
import project.com.isly.models.Student;

/**
 * Created by juan on 13/10/15.
 */
public class Statistics extends Fragment {
    RecyclerView rv;
    RecyclerAdapter adapter;
    List<Student> items;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statistics,container,false);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
        items= new ArrayList<>();
        adapter=new RecyclerAdapter(items);
        rv.setAdapter(adapter);


        return v;
    }
}
