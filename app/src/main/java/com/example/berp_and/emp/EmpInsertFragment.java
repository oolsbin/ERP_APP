package com.example.berp_and.emp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EmpInsertFragment extends Fragment {

    EditText edt_employee_name_insert, edt_employee_email_insert, edt_employee_phone_insert;
    AutoCompleteTextView spinner_department_insert, spinner_company_insert, spinner_position_insert;
    RadioGroup rdg_pattern_insert, rdg_admin_insert;
    Button btn_emp_insert, btn_emp_insert_close;

    ArrayList<EmpVO> department_list = new ArrayList<>();
    ArrayList<EmpVO> company_list = new ArrayList<>();
    ArrayList<EmpVO> position_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    ArrayList<String> company_list_real = new ArrayList<>();
    ArrayList<String> position_list_real = new ArrayList<>();
    int new_emp_id;
    int department_id;
    String company_cd, position;

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.container_state =1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_emp_insert, container, false);
        MainActivity.toolbar.setTitle("신규입력");
        MainActivity.container_state =1;
        edt_employee_name_insert = v.findViewById(R.id.edt_employee_name_insert);
        edt_employee_email_insert = v.findViewById(R.id.edt_employee_email_insert);
        edt_employee_phone_insert = v.findViewById(R.id.edt_employee_phone_insert);
        spinner_department_insert = v.findViewById(R.id.spinner_department_insert);
        spinner_company_insert = v.findViewById(R.id.spinner_company_insert);
        spinner_position_insert = v.findViewById(R.id.spinner_position_insert);
        rdg_pattern_insert = v.findViewById(R.id.rdg_pattern_insert);
        rdg_admin_insert = v.findViewById(R.id.rdg_admin_insert);
        btn_emp_insert = v.findViewById(R.id.btn_emp_insert);
        btn_emp_insert_close = v.findViewById(R.id.btn_emp_insert_close);



        CommonAskTask askTask_department = new CommonAskTask("andEmpListDepartment.hr", getContext());
        askTask_department.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                department_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < department_list.size(); i++) {
                    department_list_real.add(department_list.get(i).getDepartment_name());
                }

            }
        });
        CommonAskTask askTask_company = new CommonAskTask("andEmpListCompany.hr", getContext());
        askTask_company.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                company_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < company_list.size(); i++) {
                    company_list_real.add(company_list.get(i).getCompany_name());
                }


            }
        });
        CommonAskTask askTask_position = new CommonAskTask("andEmpListPosition.hr", getContext());
        askTask_position.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                position_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < position_list.size(); i++) {
                    position_list_real.add(position_list.get(i).getPosition_name());
                }
            }
        });

        spinner_maker();

        btn_emp_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new EmpFragment()).commit();

                EmpAndInsertDTO dto = new EmpAndInsertDTO();
                dto.setDepartment_id(department_id);
                dto.setCompany_cd(company_cd);
                dto.setPosition(position);
                dto.setName(edt_employee_name_insert.getText()+"");
                dto.setEmail(edt_employee_email_insert.getText()+"");
                dto.setPhone(edt_employee_phone_insert.getText()+"");
                dto.setAdmin(rdg_admin_insert.getCheckedRadioButtonId() == R.id.emp_admin_Y ? "Y" : "N");
                dto.setEmployee_pattern(rdg_pattern_insert.getCheckedRadioButtonId() == R.id.emp_pattern_H101 ? "H101" : "H102");

                   CommonAskTask askTask = new CommonAskTask("andInsertEmployee.hr", getContext());
                    askTask.addParam("dto",  new Gson().toJson(dto));
                    askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                        @Override
                        public void onResult(String data, boolean isResult) {

                        }
                    });
            }
        });



        btn_emp_insert_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new EmpFragment()).commit();


            }
        });




        return v;
    }


    public void spinner_maker(){

        spinner_department_insert.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.emp_drop_down_item, department_list_real ));
        spinner_company_insert.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.emp_drop_down_item, company_list_real ));
        spinner_position_insert.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.emp_drop_down_item, position_list_real ));

        spinner_department_insert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                department_id = department_list.get(i).getDepartment_id();
            }
        });

        spinner_company_insert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                company_cd = company_list.get(i).getCompany_cd();
            }
        });

        spinner_position_insert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                position = position_list.get(i).getPosition();
            }
        });
    }
}
