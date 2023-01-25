package com.example.berp_and.emp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.berp_and.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmpListAdapter extends RecyclerView.Adapter<EmpListAdapter.EmpListHolder> {

    LayoutInflater inflater;
    ArrayList<EmpVO> list;
    Context context;
    ArrayList<String> emp_picList;

    public EmpListAdapter(LayoutInflater inflater, ArrayList<EmpVO> list, Context context, ArrayList<String> emp_picList) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.emp_picList = emp_picList;
    }

    @NonNull
    @Override
    public EmpListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmpListHolder(inflater.inflate(R.layout.item_emplist, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull EmpListHolder h, @SuppressLint("RecyclerView") int i) {


        Glide.with(context).load(emp_picList.get(i)).into(h.img_face);
        h.tv_name_emp.setText(list.get(i).getName()+" ("+list.get(i).getEmployee_id()+")");
        h.tv_dept_position.setText(list.get(i).getPosition_name()+" / "+list.get(i).getDepartment_name()+" / "+list.get(i).getHire_date());

        h.img_empSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EmpDetailActivity.class);
                intent.putExtra("employee_id", list.get(i).getEmployee_id());
                intent.putExtra("name", list.get(i).getName());
                intent.putExtra("department_id", list.get(i).getDepartment_id());
                intent.putExtra("department_name", list.get(i).getDepartment_name());
                intent.putExtra("company", list.get(i).getCompany_cd());
                intent.putExtra("company_name", list.get(i).getCompany_name());
                intent.putExtra("position", list.get(i).getPosition());
                intent.putExtra("position_name", list.get(i).getPosition_name());
                intent.putExtra("pattern", list.get(i).getEmployee_pattern());
                intent.putExtra("admin", list.get(i).getAdmin());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmpListHolder extends RecyclerView.ViewHolder{
        CircleImageView img_face;
        ImageView img_empSearch;
        TextView tv_name_emp, tv_dept_position;

        public EmpListHolder(@NonNull View v) {
            super(v);

            img_empSearch = v.findViewById(R.id.img_empSearch);
            img_face = v.findViewById(R.id.img_face);
            tv_name_emp = v.findViewById(R.id.tv_name_emp);
            tv_dept_position = v.findViewById(R.id.tv_dept_position);

        }
    }
}
