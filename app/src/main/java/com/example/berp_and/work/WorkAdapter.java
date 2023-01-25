package com.example.berp_and.work;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.berp_and.R;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {
    LayoutInflater inflater ;
    ArrayList<WorkResultVO> list;
    Context context;

    public WorkAdapter(LayoutInflater inflater, ArrayList<WorkResultVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.work_item_ex,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_employee_id.setText(list.get(i).getEmployee_id()+"");
        h.tv_employee_name.setText(list.get(i).getName());
        h.tv_employee_department.setText(list.get(i).getDepartment_name());

        if(list.get(i).getWork_date() == null){
            h.work_date.setText("-");
        }else{
            h.work_date.setText(list.get(i).getWork_date()+"");
        }
        if(list.get(i).getStart_work() ==null){
            h.start_work.setText("-");
        }else{
        h.start_work.setText(list.get(i).getStart_work());
        }
        if(list.get(i).getEnd_work() == null){
            h.end_work.setText("-");
        }else{
        h.end_work.setText(list.get(i).getEnd_work());
        }

        if(list.get(i).getWork_status() == null){
            h.work_status.setText("-");
        }else{
            h.work_status.setText(list.get(i).getWork_status());
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_employee_id, tv_employee_name, tv_employee_department,work_date, start_work,end_work, work_status;
    LinearLayout layout_back;

        public ViewHolder(@NonNull View v) {
            super(v);
            tv_employee_id = v.findViewById(R.id.tv_employee_id);
            tv_employee_name = v.findViewById(R.id.tv_employee_name);
            tv_employee_department = v.findViewById(R.id.tv_employee_department);
            work_date = v.findViewById(R.id.work_date);
            start_work = v.findViewById(R.id.start_work);
            end_work = v.findViewById(R.id.end_work);
            work_status = v.findViewById(R.id.work_status);
            layout_back = v.findViewById(R.id.layout_back);
        }
    }

}
