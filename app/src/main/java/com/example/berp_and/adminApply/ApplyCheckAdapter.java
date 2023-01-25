package com.example.berp_and.adminApply;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class ApplyCheckAdapter extends RecyclerView.Adapter<ApplyCheckAdapter.ApplyCheckHolder> {

    LayoutInflater inflater;
    ArrayList<ApplyVO> list;
    Context context;

    public ApplyCheckAdapter(LayoutInflater inflater, ArrayList<ApplyVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplyCheckHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplyCheckHolder(inflater.inflate(R.layout.item_apply_check_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyCheckHolder h, @SuppressLint("RecyclerView") int i) {

        h.tv_apply_dual_num.setText(i+1+"");
        h.tv_apply_check_name.setText(list.get(i).getApply_name());
        h.tv_apply_check_title.setText(list.get(i).getRecruit_title());
        h.tv_apply_check_date.setText(list.get(i).getApply_date()+"");
        if (list.get(i).getApply_date()==null){
            h.tv_apply_check_date.setText("지원자 없음");
        }

        h.linear_apply_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ApplyCheckDetailActivity.class);
                intent.putExtra("vo", list.get(i));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ApplyCheckHolder extends RecyclerView.ViewHolder {

    LinearLayout linear_apply_list;
    TextView tv_apply_dual_num, tv_apply_check_name, tv_apply_check_title, tv_apply_check_date;

        public ApplyCheckHolder(@NonNull View v) {
            super(v);
            linear_apply_list = v.findViewById(R.id.linear_apply_list);
            tv_apply_dual_num = v.findViewById(R.id.tv_apply_dual_num);
            tv_apply_check_name = v.findViewById(R.id.tv_apply_check_name);
            tv_apply_check_title = v.findViewById(R.id.tv_apply_check_title);
            tv_apply_check_date = v.findViewById(R.id.tv_apply_check_date);
        }
    }
}
