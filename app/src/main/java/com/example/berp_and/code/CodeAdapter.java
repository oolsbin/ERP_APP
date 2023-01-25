package com.example.berp_and.code;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;
import com.example.berp_and.work.CommonCodeVO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.CodeHolder> {
    LayoutInflater inflater;
    ArrayList<CommonCodeVO> list;
    Context context;


    public CodeAdapter(LayoutInflater inflater, ArrayList<CommonCodeVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_codelist, parent, false);
        return new CodeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeHolder h, @SuppressLint("RecyclerView") int i) {
        h.code_personal.setText(list.get(i).getCode_comment());
        h.code_document.setText(list.get(i).getCode_value());
        h.code_approve.setText(list.get(i).getCode_used());
        h.code_work.setText(list.get(i).getCode_name());



        h.linear_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CodeDetailActivity.class);

                intent.putExtra("vo", (Serializable) list.get(i));

                context.startActivity(intent);

                /*h.code_date.getContext().startActivity(intent);*/
                /*                h.code_name.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CodeHolder extends RecyclerView.ViewHolder {
        TextView code_personal, code_document, code_approve, code_work, code_date, code_name;
        LinearLayout linear_code;
        public CodeHolder(@NonNull View v) {
            super(v);
            code_personal = v.findViewById(R.id.code_personal);
            code_document = v.findViewById(R.id.code_document);
            code_approve = v.findViewById(R.id.code_approve);
            code_work = v.findViewById(R.id.code_work);

            linear_code = v.findViewById(R.id.linear_code);

        }
    }
}
