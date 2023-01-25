package com.example.berp_and.work;

import android.os.Bundle;

import androidx.core.util.Pair;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpVO;
import com.example.berp_and.login.LoginActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.CalendarConstraints;
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


public class HolidayInsertFragment extends BottomSheetDialogFragment {

    Button holiday_date, holiday_insert_btn;
    HolidayVO vo = new HolidayVO();
    TextView holiday_start, holiday_end;
    ArrayList<CommonCodeVO> code_list = new ArrayList<>();
    ArrayList<String> code_list_real = new ArrayList<>();
    AutoCompleteTextView workCode_item_filled_exposed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_holiday_insert, container, false);

        holiday_date = v.findViewById(R.id.holiday_date);
        holiday_insert_btn = v.findViewById(R.id.holiday_insert_btn);
        holiday_start = v.findViewById(R.id.holiday_start);
        holiday_end = v.findViewById(R.id.holiday_end);
        workCode_item_filled_exposed = v.findViewById(R.id.workCode_item_filled_exposed);

        value_add();

        workCode_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                workCode_item_filled_exposed.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                        R.layout.emp_drop_down_item, code_list_real));


            }
        });
        workCode_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                workCode_item_filled_exposed.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                        R.layout.emp_drop_down_item, code_list_real));

                vo.setWork_code(code_list.get(i).getCode_value());


            }
        });

        holiday_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder =
                        MaterialDatePicker.Builder.dateRangePicker().setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar);
                builder.setTitleText("날짜를 선택해주세요");
                Log.d("test", "onClick: " + Calendar.getInstance().getTimeInMillis());
                builder.setCalendarConstraints(new CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.now()).build());


                MaterialDatePicker materialDatePicker = builder.build();


                materialDatePicker.show(getChildFragmentManager(),"DATE_PICKER");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Log.d("test", "onPositiveButtonClick: " + selection);
                        Pair<Long , Long> pair = (Pair<Long, Long>) selection;

                        Log.d("test", "onPositiveButtonClick: " +     pair.first);
                        Log.d("test", "onPositiveButtonClick: " +     new Date(pair.first));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);


                        vo.setEmployee_id(LoginActivity.loginInfoList.get(0).getEmployee_id()+"");
                        vo.setDepartment_id(LoginActivity.loginInfoList.get(0).getDepartment_id()+"");
                        vo.setCompany_cd(LoginActivity.loginInfoList.get(0).getCompany_cd());
                        vo.setStart_holiday(sdf.format(new Date(pair.first)));
                        vo.setEnd_holiday(sdf.format(new Date(pair.second)));
                        holiday_start.setText("휴가 시작일 : " + vo.getStart_holiday());
                        holiday_end.setText("휴가 종료일 : " +vo.getEnd_holiday());
                        if(vo.getStart_holiday() == null || vo.getEnd_holiday() ==null){

                            holiday_insert_btn.isEnabled();
                        }else {
                            insert_btn();

                        }
                    }
                });

            }
        });





        return v;
    }

        public void holiday(){
            CommonAskTask askTask = new CommonAskTask("andHoliday",getActivity());

            askTask.addParam("vo",  new Gson().toJson(vo));

            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("TAG", "onResult: "+data);
                    if(data.equals("1")){
                        Toast.makeText(getContext(),"이미 신청된 휴가일 입니다.",1000*3).show();
                    }else {
                        Toast.makeText(getContext(),"휴가 신청완료 ^3^",1000*3).show();
                    }
                }
            });

        }
        /*public void andHolidaySearch(){
        CommonAskTask askTask = new CommonAskTask("andHolidaySearch", getActivity());
        askTask.addParam("vo",  new Gson().toJson(vo));
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {

            }
        });
        }
*/


    public void insert_btn(){
        holiday_insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holiday();

            }
        });
    }
    public void value_add() {
        CommonAskTask askTask_department = new CommonAskTask("andCode", getContext());
        askTask_department.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                code_list = new Gson().fromJson(data, new TypeToken<ArrayList<CommonCodeVO>>() {
                }.getType());
                for (int i = 0; i < code_list.size(); i++) {
                    code_list_real.add(code_list.get(i).getCode_name());
                }
                workCode_item_filled_exposed.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        code_list_real));
            }
        });
    }
}
