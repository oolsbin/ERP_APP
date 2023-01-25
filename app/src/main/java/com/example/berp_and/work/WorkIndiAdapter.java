package com.example.berp_and.work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class WorkIndiAdapter extends RecyclerView.Adapter<WorkIndiAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<WorkResultVO>list;
    Context context;


    public WorkIndiAdapter(LayoutInflater inflater, ArrayList<WorkResultVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.holi_indi_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {


         h.tv_workDate.setText(list.get(i).getWork_date().substring(5,7)+"월 " +list.get(i).getWork_date().substring(8,10)+ "일");

        h.tv_work.setText(list.get(i).getWork_status()+"");
        h.tv_workStart.setText(list.get(i).getStart_work()+"");
        h.tv_workEnd.setText(list.get(i).getEnd_work()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_workDate, tv_work, tv_workStart, tv_workEnd;
        public ViewHolder(@NonNull View v) {
            super(v);

            tv_workDate = v.findViewById(R.id.tv_workDate);
            tv_work = v.findViewById(R.id.tv_work);
            tv_workStart = v.findViewById(R.id.tv_workStart);
            tv_workEnd = v.findViewById(R.id.tv_workEnd);




        }
    }


}
