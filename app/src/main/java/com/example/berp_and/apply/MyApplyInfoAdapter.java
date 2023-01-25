package com.example.berp_and.apply;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class MyApplyInfoAdapter extends RecyclerView.Adapter<MyApplyInfoAdapter.MyInfoHolder> {
    LayoutInflater inflater;
    ArrayList<ApplyVO> list;

    public MyApplyInfoAdapter(LayoutInflater inflater, ArrayList<ApplyVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public MyInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInfoHolder(inflater.inflate(R.layout.item_my_apply_board, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoHolder h, int i) {
        h.tv_my_rec_company.setText(list.get(i).getCompany_name());
        h.tv_my_rec_title.setText(list.get(i).getRecruit_title());
        h.tv_my_rec_career.setText(list.get(i).getCareer_name());
        h.tv_my_rec_employee_pattern.setText(list.get(i).getEmployee_pattern_name());
        h.tv_my_rec_salary.setText("[연봉]" + list.get(i).getSalary() +"만원");
        h.tv_my_rec_date.setText(list.get(i).getApply_date()+"");

        if(list.get(i).getApply_check().equals("X")){
            h.view_myApply_check.setBackgroundColor(Color.parseColor("#EA3737"));
        }else if(list.get(i).getApply_check().equals("Y")){
            h.view_myApply_check.setBackgroundColor(Color.parseColor("#008EFF"));
        }else if(list.get(i).getApply_check().equals("N")){
            h.view_myApply_check.setBackgroundColor(Color.parseColor("#B1B1B1"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyInfoHolder extends RecyclerView.ViewHolder{
        TextView tv_my_rec_company, tv_my_rec_title, tv_my_rec_date, tv_my_rec_employee_pattern, tv_my_rec_salary, tv_my_rec_career;
        View view_myApply_check;

        public MyInfoHolder(@NonNull View v) {
            super(v);
            tv_my_rec_company = v.findViewById(R.id.tv_my_rec_company);
            tv_my_rec_title = v.findViewById(R.id.tv_my_rec_title);
            tv_my_rec_date = v.findViewById(R.id.tv_my_rec_date);
            tv_my_rec_employee_pattern = v.findViewById(R.id.tv_my_rec_employee_pattern);
            tv_my_rec_salary = v.findViewById(R.id.tv_my_rec_salary);
            tv_my_rec_career = v.findViewById(R.id.tv_my_rec_career);
            view_myApply_check = v.findViewById(R.id.view_myApply_check);
        }
    }
}
