package project.com.isly.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import project.com.isly.R;
import project.com.isly.models.Student;

/**
 * Created by juan on 17/10/15.
 */
public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ItemsViewHolder>{
    List<Student> students;

    public RecyclerAdapter(List<Student> students){
        this.students=students;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemsadapter, viewGroup, false);
        ItemsViewHolder pvh = new ItemsViewHolder(v);
        return pvh;
    }



    @Override
    public void onBindViewHolder(final ItemsViewHolder itemsViewHolder, int i) {
       itemsViewHolder.tvTitle.setText(students.get(i).getName());
       itemsViewHolder.tvMac.setText(students.get(i).getMac());
    }

    @Override
    public int getItemCount() {
        try {
            return students.size();
        }catch(Exception ex){}
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView tvMac,tvTitle;

        ItemsViewHolder(View itemView){
            super(itemView);
            cv=(CardView)itemView.findViewById(R.id.cv);
            tvMac=(TextView)itemView.findViewById(R.id.tvMac);
            tvTitle=(TextView)itemView.findViewById(R.id.tvTitle);
        }
    }

}