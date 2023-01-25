package com.example.berp_and.notice;

import static com.example.berp_and.R.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;

import java.io.Serializable;
import java.util.ArrayList;

public class NoticeListAdapter  extends RecyclerView.Adapter<NoticeListAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<NoticeVO> list;
    Context context;


    public NoticeListAdapter(LayoutInflater inflater, ArrayList<NoticeVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(layout.notice_list_item,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListAdapter.ViewHolder h, @SuppressLint("RecyclerView") int i) {
        h.tv_notice_date.setVisibility(View.VISIBLE);
        h.linear_notice.setVisibility(View.VISIBLE);

        if (Integer.parseInt(list.get(i).getNotice_date().substring(8)) <20){
            h.tv_notice_date.setBackgroundColor(Color.parseColor("#E1938D"));

        }



    h.notice_title.setText(list.get(i).getNotice_title());
    h.notice_writer.setText(list.get(i).getNotice_writer());
    h.tv_notice_date.setText(list.get(i).getNotice_date().substring(8)+"일");
    if (i != 0){
        if(list.get(i).getNotice_date().equals(list.get(i-1).getNotice_date())) {
            h.tv_notice_date.setVisibility(View.GONE);
        }
    }
    h.lin_notice_list.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, NoticeDetailActivity.class);
            intent.putExtra("vo", (Serializable) list.get(i));
            context.startActivity(intent);

            CommonAskTask askTask = new CommonAskTask("detail_notice_list", context);
            askTask.addParam("notice_num",list.get(i).getNotice_num()+"");
            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("TAG", "onResult: "+data);

                }
            });
        }
    });

    }

    @Override
    public int getItemCount() {


            return list.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notice_title,notice_writer,tv_notice_date;
        LinearLayout lin_notice_list,linear_notice;


        public ViewHolder(@NonNull View v) {
            super(v);
            tv_notice_date = v.findViewById(id.tv_notice_date);
            notice_title = v.findViewById(id.notice_title);
            notice_writer = v.findViewById(id.notice_writer);
            lin_notice_list = v.findViewById(id.lin_notice_list);
            linear_notice = v.findViewById(id.linear_notice);


        }
    }


}
