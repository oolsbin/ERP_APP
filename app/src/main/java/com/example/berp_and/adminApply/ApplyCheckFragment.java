package com.example.berp_and.adminApply;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ApplyCheckFragment extends Fragment {
    RecyclerView recv_applyCheck;
    AutoCompleteTextView apply_check_spinner;

    ArrayList<RecruitVO> apply_title_list;
    ArrayList<String> apply_title_list_real= new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_apply_check, container, false);
        MainActivity.toolbar.setTitle("지원자 조회");

        MainActivity.container_state = 1;
        recv_applyCheck =v.findViewById(R.id.recv_applyCheck);
        apply_check_spinner = v.findViewById(R.id.apply_check_spinner);





        function();

        spinner_import();





        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        function();
        MainActivity.container_state = 1;
    }

    public void spinner_import(){
        CommonAskTask askTask = new CommonAskTask("andApplyCheckSelect.rec", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                apply_title_list_real.clear();
                apply_title_list = new Gson().fromJson(data, new TypeToken<ArrayList<RecruitVO>>() {
                }.getType());
                apply_check_spinner.setText("전체");
                apply_title_list_real.add("전체");
                for (int i = 0; i < apply_title_list.size(); i++) {
                    apply_title_list_real.add(apply_title_list.get(i).getRecruit_title());
                }
                apply_check_spinner.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        apply_title_list_real
                ));


            }
        });
    }
    public void function(){
        CommonAskTask askTask_list = new CommonAskTask("andApplyCheckSelectList.rec" ,getActivity());
        askTask_list.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<ApplyVO> apply_title_list = new Gson().fromJson(data, new TypeToken<ArrayList<ApplyVO>>() {
                }.getType());

                recv_applyCheck.setAdapter(new ApplyCheckAdapter(getLayoutInflater(), apply_title_list, getContext()));
                recv_applyCheck.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            }
        });

        apply_check_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if(i == 0){
                    CommonAskTask askTask_list = new CommonAskTask("andApplyCheckSelectList.rec" ,getActivity());
                    askTask_list.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            ArrayList<ApplyVO> apply_title_list = new Gson().fromJson(data, new TypeToken<ArrayList<ApplyVO>>() {
                            }.getType());

                            recv_applyCheck.setAdapter(new ApplyCheckAdapter(getLayoutInflater(), apply_title_list, getContext()));
                            recv_applyCheck.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
                        }
                    });
                }else {
                    CommonAskTask askTask = new CommonAskTask("andApplyCheckSelectOne.rec", getActivity());
                    askTask.addParam("title", apply_title_list.get(i + 1).getRecruit_title());
                    askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            ArrayList<ApplyVO> apply_title_one = new Gson().fromJson(data, new TypeToken<ArrayList<ApplyVO>>() {
                            }.getType());

                            recv_applyCheck.setAdapter(new ApplyCheckAdapter(getLayoutInflater(), apply_title_one, getContext()));
                            recv_applyCheck.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        }
                    });
                }
            }
        });
    }
}