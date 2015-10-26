package project.com.isly.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;

import project.com.isly.R;
import project.com.isly.helpers.DBH;
import project.com.isly.models.Lists;

/**
 * Created by juan on 13/10/15.
 */
public class Add extends Fragment {
    private Spinner spinnerListas;
    private Button btnSetActive;
    private android.support.design.widget.FloatingActionButton fabAdd0;
    protected ArrayAdapter<CharSequence> adapter;
    private String selected;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add,container,false);
        spinnerListas=(Spinner)v.findViewById(R.id.spinnerListas);

        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, DBH.loadLists(getActivity().getApplicationContext()));
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        btnSetActive=(Button)v.findViewById(R.id.btnSetActive);
        btnSetActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected != null){

                }else{

                }
            }
        });

        fabAdd0=(android.support.design.widget.FloatingActionButton)v.findViewById(R.id.fabAdd0);
        fabAdd0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater= LayoutInflater.from(getActivity().getApplicationContext());
                View prompt= layoutInflater.inflate(R.layout.dialog_new_list, null);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(prompt);

                final EditText etNameList= (EditText) prompt.findViewById(R.id.etNameList);
                final EditText etKeyList=(EditText) prompt.findViewById(R.id.etKeyList);
                final TextView tvErrorList=(TextView) prompt.findViewById(R.id.tvErrorList);

                alertDialog
                        .setTitle(R.string.newList)
                        .setIcon(R.drawable.add_list)
                        .setCancelable(true)
                        .setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    if(etNameList.getText().toString().trim().equals("") || etKeyList.getText().toString().trim().equals("")){
                                        tvErrorList.setText(R.string.new_list_error);
                                    }else{
                                        DBH.setNewList(getActivity().getApplicationContext(),new Lists(etNameList.getText().toString().trim(),etKeyList.getText().toString().trim(),"0"));
                                    }
                                }catch (Exception ex){
                                    Toast.makeText(getActivity().getApplicationContext(),"System Error",Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert=alertDialog.create();
                        alert.show();
            }
        });
        spinnerListas.setAdapter(adapter);
        spinnerListas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }
}
