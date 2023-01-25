package com.example.berp_and.salary;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BonusListFragment extends Fragment {

    RecyclerView recv_bonusList;

    Spinner spinner_bonus;
    TextView spinner_bonus_tv;

    String department_name= "전체";
    ArrayList<DeptVO> deptList;
    String[] items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bonus_list, container, false);

        MainActivity.container_state = 1;

        recv_bonusList = v.findViewById(R.id.recv_bonusList);
        spinner_bonus = v.findViewById(R.id.spinner_bonus);
        spinner_bonus_tv = v.findViewById(R.id.spinner_bonus_tv);


        MainActivity.toolbar.setTitle("상여금 지급내역 ");

        CommonAskTask askTask = new CommonAskTask("andDepartments.sa", getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    deptList = new Gson().fromJson(data, new TypeToken<ArrayList<DeptVO>>() {
                    }.getType());

                    items = new String[deptList.size()+1];

                    items[0] = "전체";
                    for(int i = 0; i < deptList.size(); i++){
                        items[i+1] = deptList.get(i).getDepartment_name();
                    }

                    setSpinnerList();

                }else{
                    Log.d("로그", "onResult: 통신 실패");
                }

            }
        });


        return v;
    }//onCreateView

    public void setSpinnerList(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_bonus.setAdapter(arrayAdapter);
        spinner_bonus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department_name = items[position];
                spinner_bonus_tv.setText(items[position]);
                bonusList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_bonus_tv.setText("전체");
            }
        });
    }

    public void bonusList(){
        CommonAskTask askTask = new CommonAskTask("andBonusList.sa", getActivity());
        askTask.addParam("department_name", department_name);
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("로그", "onResult: "+data);
                ArrayList<BonusVO> bonusList = new Gson().fromJson(data, new TypeToken<ArrayList<BonusVO>>() {
                }.getType());

                BonusAdapter adapter = new BonusAdapter(getLayoutInflater(), getContext(), bonusList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                recv_bonusList.setAdapter(adapter);
                recv_bonusList.setLayoutManager(manager);

            }
        });
    }//salaryList
}//class