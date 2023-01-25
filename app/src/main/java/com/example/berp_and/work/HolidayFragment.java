package com.example.berp_and.work;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class HolidayFragment extends Fragment {
        RecyclerView recv_holList;
    ArrayList<EmpVO> holiday_list = new ArrayList<>();
    ArrayList<String> holiday_list_real = new ArrayList<>();
    AutoCompleteTextView hol_item_filled_exposed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_holiday, container, false);
        MainActivity.container_state = 1 ;

        recv_holList = v.findViewById(R.id.recv_holList);
        hol_item_filled_exposed = v.findViewById(R.id.hol_item_filled_exposed);
        MainActivity.toolbar.setTitle("휴일관리");
            origin_list();

            value_add();


        hol_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                hol_item_filled_exposed.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                        R.layout.emp_drop_down_item, holiday_list_real));


            }
        });
        hol_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                holiday_list_real.get(i);


                CommonAskTask askTask = new CommonAskTask("andHolidayDept_List", getContext());
                askTask.addParam("department_id", holiday_list.get(i).getDepartment_id());
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("TAG", "onResult: " + data);
                        ArrayList<HolidayVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<HolidayVO>>() {
                        }.getType());

                        recv_holList.setAdapter(new HolidayAdapter(getLayoutInflater(), list, getContext()));
                        recv_holList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    }
                });

            }
        });
        return v;
    }
    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andHoliday_List", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<HolidayVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<HolidayVO>>() {
                }.getType());

                recv_holList.setAdapter(new HolidayAdapter(getLayoutInflater(),list,getContext()));
                recv_holList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }
    public void value_add() {
        CommonAskTask askTask_department = new CommonAskTask("andWorkDept", getContext());
        askTask_department.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                holiday_list_real.clear();
                holiday_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < holiday_list.size(); i++) {
                    holiday_list_real.add(holiday_list.get(i).getDepartment_name());
                }
                hol_item_filled_exposed.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        holiday_list_real));
            }
        });
    }
}