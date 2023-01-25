package com.example.berp_and.salary;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;
import com.example.berp_and.work.WorkAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class BonusAdapter extends RecyclerView.Adapter<BonusAdapter.BonusHolder> {

    LayoutInflater layoutInflater;
    Context context;
    ArrayList<BonusVO> bonusList;

    public BonusAdapter(LayoutInflater layoutInflater, Context context, ArrayList<BonusVO> bonusList) {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.bonusList = bonusList;
    }

    @NonNull
    @Override
    public BonusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_bonuslist, parent, false);
        return new BonusHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BonusHolder h, int i) {
        h.tv_bonus_bonusList.setText(bonusList.get(i).getBonus()+"만");

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        h.tv_date_bonusList.setText(sdf.format(bonusList.get(i).getBonus_date()+" / "));

        h.tv_date_bonusList.setText(bonusList.get(i).getBonus_date()+"");

        h.tv_name_bonusList.setText(bonusList.get(i).getDepartment_name()+" "+bonusList.get(i).getC_position()+" "+bonusList.get(i).getName()+" ");

        h.tv_comment_bonusList.setText(bonusList.get(i).getBonus_comment());

        if (bonusList.get(i).getDepartment_id() == 10){
            h.view_color.setBackgroundColor(Color.parseColor("#000000"));
        }else if(bonusList.get(i).getDepartment_id() == 20){
            h.view_color.setBackgroundColor(Color.parseColor("#EA3737"));
        }else if(bonusList.get(i).getDepartment_id() == 30){
            h.view_color.setBackgroundColor(Color.parseColor("#679333"));
        }else if(bonusList.get(i).getDepartment_id() == 40){
            h.view_color.setBackgroundColor(Color.parseColor("#C6B203"));
        }else if(bonusList.get(i).getDepartment_id() == 50){
            h.view_color.setBackgroundColor(Color.parseColor("#008EFF"));
        }else if(bonusList.get(i).getDepartment_id() == 60){
            h.view_color.setBackgroundColor(Color.parseColor("#ED00FF"));
        }
    }

    @Override
    public int getItemCount() {
        return bonusList.size();
    }

    class BonusHolder extends RecyclerView.ViewHolder {

        TextView tv_bonus_bonusList, tv_date_bonusList, tv_name_bonusList, tv_comment_bonusList;
        View view_color;
        public BonusHolder(@NonNull View v) {
            super(v);
            tv_bonus_bonusList = v.findViewById(R.id.tv_bonus_bonusList);
            tv_date_bonusList = v.findViewById(R.id.tv_date_bonusList);
            tv_name_bonusList = v.findViewById(R.id.tv_name_bonusList);
            tv_comment_bonusList = v.findViewById(R.id.tv_comment_bonusList);
            view_color = v.findViewById(R.id.view_color);

        }
    }
}
