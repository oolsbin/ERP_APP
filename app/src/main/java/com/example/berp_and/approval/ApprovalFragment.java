package com.example.berp_and.approval;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpVO;
import com.example.berp_and.notice.NoticeListAdapter;
import com.example.berp_and.notice.NoticeVO;
import com.example.berp_and.work.WorkAdapter;
import com.example.berp_and.work.WorkResultVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;


public class ApprovalFragment extends Fragment {

    RecyclerView recv_approval_box;
    ArrayList<Result_tableVO> approval_list = new ArrayList<>();
    ArrayList<String> approval_list_real = new ArrayList<>();
    AutoCompleteTextView approval_item_filled_exposed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.toolbar.setTitle("결재처리함");
        View v =inflater.inflate(R.layout.fragment_approval, container, false);
        origin_list();
        MainActivity.container_state = 1;

        recv_approval_box  = v.findViewById(R.id.recv_approval_box);
        approval_item_filled_exposed  = v.findViewById(R.id.approval_item_filled_exposed);


        value_add();
        approval_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                approval_item_filled_exposed.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                        R.layout.emp_drop_down_item, approval_list_real));


            }
        });

        approval_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                approval_list_real.get(i);

                CommonAskTask askTask = new CommonAskTask("andCodeList", getContext());
                askTask.addParam("document_check", approval_list.get(i).getDocument_check()+"");
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("TAG", "onResult: " + data);
                        ArrayList<Result_tableVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<Result_tableVO>>() {
                        }.getType());

                        recv_approval_box.setAdapter(new ApprovalAdapter(getLayoutInflater(), list, getContext()));
                        recv_approval_box.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    }
                });

            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.container_state = 1;
    }

    public void origin_list(){
        CommonAskTask askTask = new CommonAskTask("andApproval_list", getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("TAG", "onResult: "+data);
                ArrayList<Result_tableVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<Result_tableVO>>() {
                }.getType());

                recv_approval_box.setAdapter(new ApprovalAdapter(getLayoutInflater(),list,getContext()));
                recv_approval_box.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));




            }
        });


    }

    public void value_add() {
        CommonAskTask askTask = new CommonAskTask("andResult_code", getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                approval_list_real.clear();
                approval_list = new Gson().fromJson(data, new TypeToken<ArrayList<Result_tableVO>>() {
                }.getType());
                for (int i = 0; i < approval_list.size(); i++) {
                    approval_list_real.add(approval_list.get(i).getC_status());
                }
                approval_item_filled_exposed.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        approval_list_real));
            }
        });
    }




}