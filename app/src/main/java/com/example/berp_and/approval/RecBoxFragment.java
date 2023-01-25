package com.example.berp_and.approval;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class RecBoxFragment extends Fragment {

    RecyclerView recv_recbox;
    ArrayList<And_Ing_tableVO> list = new ArrayList<>();

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
                    View v =  inflater.inflate(R.layout.fragment_rec_box, container, false);
                    MainActivity.container_state = 1;
                    recv_recbox = v.findViewById(R.id.recv_recbox);

                    MainActivity.toolbar.setTitle("수신함");
                    function();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.container_state = 1;
        function();
    }
    public void function(){
        CommonAskTask askTask = new CommonAskTask("andRec.ap", getContext());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<And_Ing_tableVO>>() {
                }.getType());

                recv_recbox.setAdapter(new RecAdapter(getLayoutInflater(),list, getContext()));
                recv_recbox.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
            }
        });
    }
}