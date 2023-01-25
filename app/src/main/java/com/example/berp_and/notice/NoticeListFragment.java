package com.example.berp_and.notice;

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
import com.example.berp_and.apply.ApplyListAdapter;
import com.example.berp_and.apply.RecruitVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class NoticeListFragment extends Fragment {
    RecyclerView recv_notiList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(MainActivity.LoginInfo == 0) {
            MainActivity.container_state = 5;
        }else{
            MainActivity.container_state = 1;
        }
        View v = inflater.inflate(R.layout.fragment_notice_list, container, false);

        recv_notiList  = v.findViewById(R.id.recv_notiList);

        MainActivity.toolbar.setTitle("공지사항");
        notice_origin_list();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        notice_origin_list();
    }

    public void notice_origin_list(){
        CommonAskTask askTask = new CommonAskTask("notice_list" ,getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<NoticeVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<NoticeVO>>() {
                }.getType());

                recv_notiList.setAdapter(new NoticeListAdapter(getLayoutInflater(),list,getContext()));
                recv_notiList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            }
        });


    }


}