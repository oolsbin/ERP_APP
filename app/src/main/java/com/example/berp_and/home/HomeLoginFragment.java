package com.example.berp_and.home;


import android.content.Intent;
import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cliff.comparingperformancebar.PercentageProgressBar;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.approval.WriteBoxFragment;
import com.example.berp_and.emp.EmpAndInsertDTO;
import com.example.berp_and.emp.EmpVO;
import com.example.berp_and.mypage.MyPageActivity;
import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.notice.NoticeAdapter;
import com.example.berp_and.notice.NoticeVO;
import com.example.berp_and.salary.MySalaryFragment;
import com.example.berp_and.work.HolidayAdapter;
import com.example.berp_and.work.HolidayInsertFragment;
import com.example.berp_and.work.HolidayVO;
import com.example.berp_and.work.WorkVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;


public class HomeLoginFragment extends Fragment {

    TextView tv_main_login_name;
    ImageView img_main_login_setting, img_super1, img_super2;
    TextView start_work_text, end_work_text;

        Button start_work_btn, end_work_btn, holiday_submit_btn;


    PercentageProgressBar valueProgressBar;

    public static int i = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_login, container, false);


        tv_main_login_name = v.findViewById(R.id.tv_main_login_name);
        img_main_login_setting = v.findViewById(R.id.img_main_login_setting);


        valueProgressBar = v.findViewById(R.id.valueProgressBar);
        valueProgressBar.setProgress((float) 23/30*100);

       // first_notion();
        MainActivity.toolbar.setTitle("YM NetWork");





        tv_main_login_name.setText(LoginActivity.loginInfoList.get(0).getPosition_name() + " "+ LoginActivity.loginInfoList.get(0).getName()+"님 / " + LoginActivity.loginInfoList.get(0).getDepartment_name());

        start_work_btn = v.findViewById(R.id.start_work_btn);
        end_work_btn = v.findViewById(R.id.end_work_btn);
        start_work_text = v.findViewById(R.id.start_work_text);
        end_work_text = v.findViewById(R.id.end_work_text);
        holiday_submit_btn = v.findViewById(R.id.holiday_submit_btn);
        img_super1 = v.findViewById(R.id.img_super1);
        img_super2 = v.findViewById(R.id.img_super2);

        CommonAskTask askTask = new CommonAskTask("andSearch",getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(!data.equals("[]")){
                    start_work_btn.isEnabled();
                    ArrayList<WorkVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkVO>>() {
                    }.getType());
                    start_work_text.setText(list.get(0).getStart_work());

                }else{

                }
            }
        });

        holiday_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HolidayInsertFragment fragment = new HolidayInsertFragment();
                fragment.show(getActivity().getSupportFragmentManager(),fragment.getTag());
                fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.TransparentBottomSheetDialogFragment
                );

            }
        });

        askTask = new CommonAskTask("andEndSearch",getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(data != null){

                    WorkVO list = new Gson().fromJson(data, new TypeToken<WorkVO>() {
                    }.getType());
                    if(data.equals("[]")){
                    end_work_text.setText(list.getEnd_work());
                    }

                }else if (data.equals("[]")){
                    end_work_text.setText("퇴근 버튼을 눌러주세요");
                }
            }
        });




        start_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();

            }
        });
        end_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchEnd();

            }
        });
        img_main_login_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MyPage-> Activity 연결 새창띄우는거 < 저장하고 창끄기
                Intent intent = new Intent(getActivity(), MyPageActivity.class);
                startActivity(intent);

            }
        });


        img_super1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MySalaryFragment()).commit();
            }
        });
        img_super2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new  WriteBoxFragment()).commit();

            }
        });

        return v;
    }

    public void Search(){
        CommonAskTask askTask = new CommonAskTask("andSearch",getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(data.equals("[]")){
                    work_start_input();
                    Toast.makeText(getContext(),"출근되었습니다.",1000*2).show();
                }else{
                    ArrayList<WorkVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkVO>>() {
                    }.getType());
                    start_work_text.setText(list.get(0).getStart_work());
                    start_work_btn.isEnabled();
                    Toast.makeText(getContext(),"출근은 한 번만 가능합니다.",1000*2).show();
                }
            }
        });

    }
    public void SearchEnd(){
        CommonAskTask askTask = new CommonAskTask("andEndSearch",getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                        work_end_input();

            }
        });

    }

    public void work_start_input(){
        CommonAskTask askTask = new CommonAskTask("andWork_start_input", getActivity());

        String date = String.format("%02d",new Date().getHours())+":" + String.format("%02d",new Date().getMinutes())
        +":"+ String.format("%02d",new Date().getSeconds());

        WorkVO dto = new WorkVO();
        dto.setDepartment_id(LoginActivity.loginInfoList.get(0).getDepartment_id());
        dto.setEmployee_id(LoginActivity.loginInfoList.get(0).getEmployee_id());
        dto.setCompany_cd(LoginActivity.loginInfoList.get(0).getCompany_cd());
        dto.setStart_work(date);

        askTask.addParam("dto",new Gson().toJson(dto));
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(i == 0){
                    start_work_text.setText(date);
                    i++;
                }else {

                }

            }
        });
    } public void work_end_input(){
        CommonAskTask askTask = new CommonAskTask("andWork_end_input", getActivity());


        String date = String.format("%02d",new Date().getHours())+":" + String.format("%02d",new Date().getMinutes())
                +":"+ String.format("%02d",new Date().getSeconds());
        WorkVO dto = new WorkVO();
        dto.setEmployee_id(LoginActivity.loginInfoList.get(0).getEmployee_id());
        dto.setEnd_work(date);
        askTask.addParam("dto",new Gson().toJson(dto));

        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                end_work_text.setText(date);


            }
        });
    }





    public void first_notion(){
        //로그인후 푸쉬알람(본인 미결재 서류)
        CommonAskTask task = new CommonAskTask("fireApproval.fi", getActivity());
        task.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        task.addParam("name", LoginActivity.loginInfoList.get(0).getName());
        task.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {

            }
        });
    }



}