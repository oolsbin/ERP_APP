package com.example.berp_and.work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpListAdapter;
import com.example.berp_and.emp.EmpVO;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.salary.DatePickerFragment;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class WorkFragment extends Fragment {
    TextView date_work;
    RecyclerView recv_workList;
    ArrayList<EmpVO> department_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    AutoCompleteTextView work_item_filled_exposed;
    Button date_picker_work, btn_date;
    String start_date;
     int department_id;
    public  int num;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_work, container, false);
        MainActivity.toolbar.setTitle("근태관리");
        MainActivity.container_state = 1;

        recv_workList = v.findViewById(R.id.recv_workList);
        date_picker_work = v.findViewById(R.id.date_picker_work);
        date_work = v.findViewById(R.id.date_work);
        btn_date = v.findViewById(R.id.btn_date);
        /*origin_list();*/

        value_add();
        work_item_filled_exposed = v.findViewById(R.id.work_item_filled_exposed);

        work_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                work_item_filled_exposed.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                        R.layout.emp_drop_down_item, department_list_real));


            }
        });


date_picker_work.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MaterialDatePicker.Builder<Long> builder =
                MaterialDatePicker.Builder.datePicker().setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar);
        builder.setTitleText("날짜를 선택해주세요");
        Log.d("test", "onClick: " + Calendar.getInstance().getTimeInMillis());
        builder.setCalendarConstraints(new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now()).build());
        MaterialDatePicker materialDatePicker = builder.build();
        materialDatePicker.show(getChildFragmentManager(),"DATE_PICKER");
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
                start_date = sdf.format(selection);


                date_work.setText(start_date+"");

            }
        });
    }
});


        work_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                department_list_real.get(i);
                num = department_list.get(i).getDepartment_id();

            }
        });


        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andWorkDeptList", getContext());

                askTask.addParam("work_date", date_work.getText()+"" );
                askTask.addParam("department_id", num);
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("TAG", "onResult: " + data);
                        ArrayList<WorkResultVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkResultVO>>() {
                        }.getType());

                        recv_workList.setAdapter(new WorkAdapter(getLayoutInflater(), list, getContext()));
                        recv_workList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    }
                });
            }
        });
        return v;
    }


    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andWorkList", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<WorkResultVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkResultVO>>() {
                }.getType());

                recv_workList.setAdapter(new WorkAdapter(getLayoutInflater(), list, getContext()));
                recv_workList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }
    public void value_add() {
        CommonAskTask askTask_department = new CommonAskTask("andWorkDept", getContext());
        askTask_department.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                department_list_real.clear();
                department_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < department_list.size(); i++) {
                    department_list_real.add(department_list.get(i).getDepartment_name());
                }
                work_item_filled_exposed.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        department_list_real));
            }
        });
    }



}