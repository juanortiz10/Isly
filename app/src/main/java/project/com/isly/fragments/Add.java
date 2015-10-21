package project.com.isly.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import project.com.isly.R;
import project.com.isly.helpers.DBH;
import project.com.isly.models.Lists;

/**
 * Created by juan on 13/10/15.
 */
public class Add extends Fragment {
    private Spinner spinnerListas;
    private Button btnNewList;
    protected ArrayAdapter<CharSequence> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add,container,false);
        spinnerListas=(Spinner)v.findViewById(R.id.spinnerListas);

        ArrayAdapter<Lists> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, DBH.loadLists(getActivity().getApplicationContext()));
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);


        btnNewList=(Button)v.findViewById(R.id.btnNewList);
        btnNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do a dialog input
                DBH.setNewList(getActivity().getApplicationContext(),null,null,null);
            }
        });
        spinnerListas.setAdapter(adapter);
        spinnerListas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Method to select the active list to takely
            }
        });
        return v;
    }
}
