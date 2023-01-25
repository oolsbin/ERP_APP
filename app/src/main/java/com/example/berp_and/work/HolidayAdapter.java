package com.example.berp_and.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<HolidayVO> list;
    Context context;

    public HolidayAdapter(LayoutInflater inflater, ArrayList<HolidayVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.holiday_item_ex, parent,false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_employee_id.setText(list.get(i).getEmployee_id()+"");
        h.tv_employee_name.setText(list.get(i).getName());
        h.hire_year.setText(list.get(i).getHire_year());
        h.tv_employee_department.setText(list.get(i).getDname());
        h.work_code.setText(list.get(i).getWork_code());
        h.holiday_date.setText(list.get(i).getHoliday_date());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_employee_id, tv_employee_name, tv_employee_department,hire_year,work_code,holiday_date;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_employee_id = v.findViewById(R.id.tv_employee_id);
            tv_employee_name = v.findViewById(R.id.tv_employee_name);
            tv_employee_department = v.findViewById(R.id.tv_employee_department);
            hire_year = v.findViewById(R.id.hire_year);
            work_code = v.findViewById(R.id.work_code);
            holiday_date = v.findViewById(R.id.holiday_date);
        }
    }
}
