package com.example.berp_and.apply;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class ApplyListAdapter extends RecyclerView.Adapter<ApplyListAdapter.ApplyListHolder> {
    LayoutInflater inflater;
    ArrayList<RecruitVO> rec_list;
    Context context;

    public ApplyListAdapter(LayoutInflater inflater, ArrayList<RecruitVO> rec_list, Context context) {
        this.inflater = inflater;
        this.rec_list = rec_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplyListHolder(inflater.inflate(R.layout.item_recruit_board, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyListHolder h, @SuppressLint("RecyclerView") int i) {
        h.tv_rec_company.setText(rec_list.get(i).getCompany_name());
        h.tv_rec_title.setText(rec_list.get(i).getRecruit_title());
        h.tv_rec_career.setText(rec_list.get(i).getCareer_name());
        h.tv_rec_employee_pattern.setText(rec_list.get(i).getEmployee_pattern_name());
        h.tv_rec_salary.setText("[연봉]" + rec_list.get(i).getSalary() +"만원");
        h.card_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ApplyDetailActivity.class);
                intent.putExtra("recruit_num" , rec_list.get(i).getRecruit_num());
                intent.putExtra("file_name" , rec_list.get(i).getFile_name());
                intent.putExtra("file_path" , rec_list.get(i).getFile_path());
                intent.putExtra("employee_pattern" , rec_list.get(i).getEmployee_pattern());
                intent.putExtra("recruit_title" , rec_list.get(i).getRecruit_title());
                intent.putExtra("recruit_content" , rec_list.get(i).getRecruit_content());
                intent.putExtra("code_value" , rec_list.get(i).getCode_value());
                intent.putExtra("code_name" , rec_list.get(i).getCode_name());
                intent.putExtra("recruit_start" , rec_list.get(i).getRecruit_start());
                intent.putExtra("recruit_end" , rec_list.get(i).getRecruit_end());
                intent.putExtra("char_recruit_start" , rec_list.get(i).getChar_recruit_start());
                intent.putExtra("char_recruit_end" , rec_list.get(i).getChar_recruit_end());
                intent.putExtra("salary" , rec_list.get(i).getSalary());
                intent.putExtra("company" , rec_list.get(i).getCompany());
                intent.putExtra("career" , rec_list.get(i).getCareer());
                intent.putExtra("company_name" , rec_list.get(i).getCompany_name());
                intent.putExtra("employee_pattern_name" , rec_list.get(i).getEmployee_pattern_name());
                intent.putExtra("career_name" , rec_list.get(i).getCareer_name());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rec_list.size();
    }

    public class ApplyListHolder extends RecyclerView.ViewHolder{
        TextView tv_rec_company, tv_rec_title, tv_rec_career, tv_rec_employee_pattern, tv_rec_salary;
        CardView card_apply;
        public ApplyListHolder(@NonNull View v) {
            super(v);

            card_apply = v.findViewById(R.id.card_apply);
            tv_rec_company = v.findViewById(R.id.tv_rec_company);
            tv_rec_title = v.findViewById(R.id.tv_rec_title);
            tv_rec_career = v.findViewById(R.id.tv_rec_career);
            tv_rec_employee_pattern = v.findViewById(R.id.tv_rec_employee_pattern);
            tv_rec_salary = v.findViewById(R.id.tv_rec_salary);
        }
    }
}
