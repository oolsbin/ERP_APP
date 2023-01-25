package com.example.berp_and.main_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;


public class MainPageFragment extends Fragment {
    private static final boolean GRID_LAYOUT = false;
   // private static final int ITEM_COUNT = 100;

    RecyclerView mRecyclerView;
    ArrayList<MainDTO> list ;
    int[] mainLogoRes = new int[]{
            R.drawable.img_menu_main2,
            R.drawable.img_menu_main1,
            R.drawable.img_menu_main8,
            R.drawable.img_menu_main7,
            R.drawable.img_menu_main3,
            R.drawable.img_menu_main5,
            R.drawable.img_menu_main4
    };

    public MainPageFragment(MainDTO dto , ArrayList<MainDTO> list , int position){
        this.list = list;
        list.add(0 , new MainDTO(dto.getMainMenu(),new ArrayList<>(),mainLogoRes[position]));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainpage, container, false);
        mRecyclerView = v.findViewById(R.id.recv);
        return v ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new RecvListAdapter(list ,(MainActivity) getActivity()));
    }

}