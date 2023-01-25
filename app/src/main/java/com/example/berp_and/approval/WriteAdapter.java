package com.example.berp_and.approval;

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


public class WriteAdapter extends RecyclerView.Adapter<WriteAdapter.WriteHolder> {
    LayoutInflater inflater;
    ArrayList<And_Ing_tableVO> list;
    Context context;

    public WriteAdapter(LayoutInflater inflater, ArrayList<And_Ing_tableVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WriteHolder(inflater.inflate(R.layout.item_rec_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WriteHolder h, @SuppressLint("RecyclerView") int i) {
        h.tv_rec_num.setText(i+1+"");
        h.tv_rec_title.setText(list.get(i).getDocument_title());
        h.tv_rec_date.setText(list.get(i).getDocument_date()+"");
        h.tv_rec_name.setText(list.get(i).getApp_position_name()+" "+list.get(i).getApp_name());

        h.linear_write_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WriteDetailActivity.class);
                intent.putExtra("vo", list.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WriteHolder extends RecyclerView.ViewHolder {
        LinearLayout linear_write_list;
        TextView tv_rec_num, tv_rec_title, tv_rec_date, tv_rec_name;
        public WriteHolder(@NonNull View v) {
            super(v);
            tv_rec_num = v.findViewById(R.id.tv_rec_num);
            tv_rec_title = v.findViewById(R.id.tv_rec_title);
            tv_rec_date = v.findViewById(R.id.tv_rec_date);
            tv_rec_name = v.findViewById(R.id.tv_rec_name);
            linear_write_list = v.findViewById(R.id.linear_write_list);

        }
    }
}
