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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpListAdapter;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SalaryListFragment extends Fragment {

    RecyclerView recv_salaryList;
    LayoutInflater inflater;

    Spinner spinner;
    String department_name= "전체";

    TextView spinner_tv;
    ArrayList<DeptVO> deptList;
    String[] items;

    EditText edt_salary_nameFind;
    Button btn_nameFind_btn;

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_salary_list, container, false);
        setInflater(inflater);

        MainActivity.container_state = 1;
        recv_salaryList = v.findViewById(R.id.recv_salaryList);
        spinner = v.findViewById(R.id.spinner);
        spinner_tv = v.findViewById(R.id.spinner_tv);
        edt_salary_nameFind = v.findViewById(R.id.edt_salary_nameFind);
        btn_nameFind_btn = v.findViewById(R.id.btn_nameFind_btn);


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

        btn_nameFind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("andSalaryName.sa", getActivity());
                task.addParam("name", edt_salary_nameFind.getText()+"");
                task.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {

                        ArrayList<SalaryVO> salaryList = new Gson().fromJson(data, new TypeToken<ArrayList<SalaryVO>>() {
                        }.getType());

                        if(salaryList != null) {
                            SalaryAdapter adapter = new SalaryAdapter(getLayoutInflater(), getContext(), salaryList, SalaryListFragment.this);
                            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                            recv_salaryList.setLayoutManager(manager);
                            recv_salaryList.setAdapter(adapter);
                        }
                    }
                });



            }
        });





        MainActivity.toolbar.setTitle("급여관리");
        return v;
    }//onCreateView

    @Override
    public void onResume() {
        super.onResume();
        salaryList();
        MainActivity.container_state = 1;
    }

    public void setSpinnerList(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department_name = items[position];
                spinner_tv.setText(items[position]);
                salaryList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_tv.setText("전체");
            }
        });
    }

    public void salaryList(){
        CommonAskTask askTask = new CommonAskTask("andSalaryList.sa", getActivity());
        askTask.addParam("department_name", department_name);
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<SalaryVO> salaryList = new Gson().fromJson(data, new TypeToken<ArrayList<SalaryVO>>() {
                }.getType());

                SalaryAdapter adapter = new SalaryAdapter(inflater, getContext(), salaryList , SalaryListFragment.this);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                recv_salaryList.setLayoutManager(manager);
                recv_salaryList.setAdapter(adapter);

            }
        });
    }//salaryList



}//SalaryListFragment