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

import java.io.Serializable;
import java.util.ArrayList;


public class TempBoxAdapter extends RecyclerView.Adapter<TempBoxAdapter.ViewHolder> {
        LayoutInflater inflater;
        ArrayList<Ing_tableVO> list;
        Context context;

    public TempBoxAdapter(LayoutInflater inflater, ArrayList<Ing_tableVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.temp_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, @SuppressLint("RecyclerView") int i) {

            h.tv_employee_name.setText(list.get(i).getEmployee_name());
            h.tv_document_title.setText(list.get(i).getDocument_title());
            h.tv_document_date.setText(list.get(i).getDocument_date()+"");

            h.linear_temp_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,TempDetailActivity.class);
                    intent.putExtra("vo", (Serializable) list.get(i));
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_employee_name,tv_document_title,tv_document_date;
        LinearLayout linear_temp_list;
        public ViewHolder(@NonNull View v) {
            super(v);

            tv_employee_name = v.findViewById(R.id.tv_employee_name);
            tv_document_title = v.findViewById(R.id.tv_document_title);
            tv_document_date = v.findViewById(R.id.tv_document_date);
            linear_temp_list = v.findViewById(R.id.linear_temp_list);

        }
    }
}
