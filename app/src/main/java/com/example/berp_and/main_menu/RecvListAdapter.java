package com.example.berp_and.main_menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

import java.util.ArrayList;

public class RecvListAdapter extends RecyclerView.Adapter<RecvListAdapter.ViewHolder> {


    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    ArrayList<MainDTO> list ;
    MainActivity activity;

    public RecvListAdapter(  ArrayList<MainDTO> list , MainActivity activity ) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_mainmenu_big, parent, false);
                return new ViewHolder(view) ;
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_mainmenu_small, parent, false);
                return new ViewHolder(view) ;
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(ViewHolder h, int i) {

        switch (getItemViewType(i)) {
            case TYPE_HEADER:
                h.tv_menu.setText(list.get(i).getMainMenu());
                h.imgv_menu_main.setImageResource(list.get(i).imgRes);
                break;
            case TYPE_CELL:
                h.tv_menu.setText(list.get(i).getMenu());
                h.ln_menu.setOnClickListener(v->{
                    activity.changeFragment(list.get(i).getFragment());
                });
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_menu ;
        ImageView imgv_menu_main ;
        LinearLayout ln_menu;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_menu = v.findViewById(R.id.tv_menu);
            imgv_menu_main = v.findViewById(R.id.imgv_menu_main);
            ln_menu = v.findViewById(R.id.ln_menu);
        }
    }
}
