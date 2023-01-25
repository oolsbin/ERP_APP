package com.example.berp_and.approval;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WriteBoxFragment extends Fragment {

    RecyclerView recv_write;
    ArrayList<And_Ing_tableVO> list = new ArrayList<>();
    Button btn_new_doc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_box, container, false);
    MainActivity.toolbar.setTitle("상신함");
        MainActivity.container_state = 1;
        recv_write = v.findViewById(R.id.recv_write);
        btn_new_doc = v.findViewById(R.id.btn_new_doc);


        function();

        btn_new_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WriteNewActivity.class);
                startActivity(intent);
            }
        });





        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.container_state = 1;
        function();
    }
    public void function(){
        CommonAskTask askTask = new CommonAskTask("andWrite.ap", getContext());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<And_Ing_tableVO>>() {
                }.getType());

                recv_write.setAdapter(new WriteAdapter(getLayoutInflater(), list, getContext()));
                recv_write.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            }
        });
    }
}