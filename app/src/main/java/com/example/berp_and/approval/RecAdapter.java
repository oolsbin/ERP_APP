package com.example.berp_and.approval;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecHolder>{

    LayoutInflater inflater;
    ArrayList<And_Ing_tableVO> list;
    Context context;

    public RecAdapter(LayoutInflater inflater, ArrayList<And_Ing_tableVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecHolder(inflater.inflate(R.layout.item_recbox, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder h, @SuppressLint("RecyclerView") int i) {
        h.tv_rec_num.setText(i+1+"");
        h.tv_rec_title.setText(list.get(i).getDocument_title()+"");
        h.tv_rec_date.setText(list.get(i).getDocument_date()+"");
        h.tv_rec_name.setText(list.get(i).getEmp_position_name()+" "+list.get(i).getEmp_name());

        h.linear_rec_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecDetailActivity.class);
                intent.putExtra("vo", list.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecHolder extends RecyclerView.ViewHolder{
        LinearLayout linear_rec_list;
        TextView tv_rec_num, tv_rec_title, tv_rec_date, tv_rec_name;
        public RecHolder(@NonNull View v) {
            super(v);
            tv_rec_num = v.findViewById(R.id.tv_rec_num);
            tv_rec_title = v.findViewById(R.id.tv_rec_title);
            tv_rec_date = v.findViewById(R.id.tv_rec_date);
            tv_rec_name = v.findViewById(R.id.tv_rec_name);
            linear_rec_list = v.findViewById(R.id.linear_rec_list);
        }
    }
}
