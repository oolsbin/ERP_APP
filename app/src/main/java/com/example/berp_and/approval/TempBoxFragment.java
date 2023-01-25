package com.example.berp_and.approval;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.work.WorkAdapter;
import com.example.berp_and.work.WorkResultVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class TempBoxFragment extends Fragment {
        RecyclerView recv_temp_box;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_temp_box, container, false);

        MainActivity.container_state = 1;
        recv_temp_box  = v.findViewById(R.id.recv_temp_box);
        MainActivity.toolbar.setTitle("임시보관함");

        origin_list();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        origin_list();
        MainActivity.container_state = 1;
    }

    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andTempList", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<Ing_tableVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<Ing_tableVO>>() {
                }.getType());

                recv_temp_box.setAdapter(new TempBoxAdapter(getLayoutInflater(), list, getContext()));
                recv_temp_box.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }



}