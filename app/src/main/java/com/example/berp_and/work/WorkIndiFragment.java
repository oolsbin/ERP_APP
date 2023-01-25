package com.example.berp_and.work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WorkIndiFragment extends Fragment {
    TextView tv_name_emp2,tv_dept_position2, tv_today_workStart, tv_today_workEnd;
    RecyclerView recv_holIndiList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.container_state = 1;
     View v = inflater.inflate(R.layout.fragment_work_indi, container, false);

        recv_holIndiList = v.findViewById(R.id.recv_holIndiList);
        tv_name_emp2 = v.findViewById(R.id.tv_name_emp2);
        tv_dept_position2 = v.findViewById(R.id.tv_dept_position2);
        tv_today_workStart = v.findViewById(R.id.tv_today_workStart);
        tv_today_workEnd = v.findViewById(R.id.tv_today_workEnd);


        origin_list();
        find_today();
        MainActivity.toolbar.setTitle("나의 출퇴근 조회");

        tv_name_emp2.setText(LoginActivity.loginInfoList.get(0).getName() + " ("+ LoginActivity.loginInfoList.get(0).getEmployee_id()+")");
        tv_dept_position2.setText(LoginActivity.loginInfoList.get(0).getPosition_name()+ " / " + LoginActivity.loginInfoList.get(0).getDepartment_name()+" / "+ LoginActivity.loginInfoList.get(0).getHire_date()+"");


     return v;
    }

    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andHolidayIndi_List", getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<WorkResultVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkResultVO>>() {
                }.getType());


                recv_holIndiList.setAdapter(new WorkIndiAdapter(getLayoutInflater(), list, getContext()));
                recv_holIndiList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }
    public void find_today(){
        CommonAskTask task = new CommonAskTask("findToday", getContext());
        task.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        task.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(data !=null){
                WorkVO list = new Gson().fromJson(data, new TypeToken<WorkVO>() {
                }.getType());

                if(list.getStart_work() != null && list.getEnd_work() != null){
                    tv_today_workStart.setText(list.getStart_work());
                    tv_today_workEnd.setText(list.getEnd_work());
                }else if(list.getEnd_work() == null) {
                    tv_today_workStart.setText(list.getStart_work());
                }

                }

            }
        });
    }

}