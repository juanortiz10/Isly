package project.com.isly.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import project.com.isly.R;
import project.com.isly.models.Students;

/**
 * Created by juan on 27/10/15.
 */
public class StudentsAdapter extends ArrayAdapter {

    public StudentsAdapter(Context context, List objects){
        super(context,0 ,objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.students_adapter,null);
        }

        TextView name= (TextView)convertView.findViewById(R.id.tvName);
        TextView mac= (TextView)convertView.findViewById(R.id.tvMacAddress);
        TextView counter= (TextView)convertView.findViewById(R.id.tvAssistance);
        TextView last=(TextView)convertView.findViewById(R.id.tvLast);

        Students student= (Students) getItem(position);
        name.setText(student.getName_student());
        mac.setText(student.getMac());
        counter.setText(getContext().getResources().getString(R.string.assistances)+": "+String.valueOf(student.getCounter()));
        last.setText(student.getLast_updated());

        return convertView;
    }
}
