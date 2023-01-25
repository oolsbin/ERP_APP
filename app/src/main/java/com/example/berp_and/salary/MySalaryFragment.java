package com.example.berp_and.salary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MySalaryFragment extends Fragment {

    RecyclerView recv_myBonusList;
    Context context;

  TextView tv_name_space, tv_salary_total;

    List<BonusVO> myBonusList;

    LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_salary, container, false);
        MainActivity.toolbar.setTitle("나의 급여 조회");
        MainActivity.container_state = 1;
        recv_myBonusList = v.findViewById(R.id.recv_myBonusList);
        tv_name_space = v.findViewById(R.id.tv_name_space);
        tv_salary_total = v.findViewById(R.id.tv_salary_total);





        CommonAskTask askTask2 = new CommonAskTask("andMyBonusList.sa", getActivity());
        askTask2.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask2.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                myBonusList = new Gson().fromJson(data, new TypeToken<List<BonusVO>>() {
                }.getType());

                MySalaryAdapter adapter = new MySalaryAdapter(getLayoutInflater(), context, myBonusList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

                recv_myBonusList.setAdapter(adapter);
                recv_myBonusList.setLayoutManager(manager);

            }
        });

        //급여는 개체 하나만 가져와서 바로 화면에 보여주고,
        CommonAskTask askTask = new CommonAskTask("andMySalaryVo.sa", getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                SalaryVO mySalaryVo = new Gson().fromJson(data, new TypeToken<SalaryVO>() {
                }.getType());

                tv_name_space.setText(mySalaryVo.getName() + "님 이번달 예상급여");

                int total_bonus = 0;
                for (int i = 0; i < myBonusList.size(); i++) {
                    total_bonus += myBonusList.get(i).getBonus();
                }
                int data_salary = mySalaryVo.getSalary()/12*10000 + (total_bonus*10000);
                DecimalFormat df = new DecimalFormat("###,###,###");
                tv_salary_total.setText(df.format(data_salary)+"원");
            }
        });

        //선 그래프

        ArrayList<Entry> entry_chart = new ArrayList<>();

        lineChart = v.findViewById(R.id.chart);//layout의 id
        LineData chartData = new LineData();
        entry_chart.add(new Entry(5*100/100, 190));
        entry_chart.add(new Entry(6*100/100, 180));
        entry_chart.add(new Entry(7*100/100, 250));
        entry_chart.add(new Entry(8*100/100, 285));
        entry_chart.add(new Entry(9*100/100, 320));
        entry_chart.add(new Entry(10*100/100, 310));
        entry_chart.add(new Entry(11*100/100, 350));

        LineDataSet lineDataSet = new LineDataSet(entry_chart, "월별 급여정보 (만원)");
        chartData.addDataSet(lineDataSet);

        lineChart.setData(chartData);

        lineChart.invalidate();






        return v;
    }
}