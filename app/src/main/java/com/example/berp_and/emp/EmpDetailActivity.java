package com.example.berp_and.emp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EmpDetailActivity extends AppCompatActivity {
    TextView tv_employee_id_detail, tv_employee_name_detail;
    AutoCompleteTextView spinner_emp_department, spinner_emp_company, spinner_emp_position;
    RadioGroup rdg_emp_admin, rdg_emp_pattern;
    Button btn_emp_close, btn_emp_delete, btn_emp_modify;

    int department_id, employee_id;
    String company_cd, position;


    ArrayList<EmpVO> department_list = new ArrayList<>();
    ArrayList<EmpVO> company_list = new ArrayList<>();
    ArrayList<EmpVO> position_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    ArrayList<String> company_list_real = new ArrayList<>();
    ArrayList<String> position_list_real = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_detail);
        MainActivity.toolbar.setTitle("직원 정보수정");

        tv_employee_id_detail = findViewById(R.id.tv_employee_id_detail);
        tv_employee_name_detail = findViewById(R.id.tv_employee_name_detail);
        spinner_emp_department = findViewById(R.id.spinner_emp_department);
        spinner_emp_company = findViewById(R.id.spinner_emp_company);
        spinner_emp_position = findViewById(R.id.spinner_emp_position);
        rdg_emp_admin = findViewById(R.id.rdg_emp_admin);
        rdg_emp_pattern = findViewById(R.id.rdg_emp_pattern);
        btn_emp_modify = findViewById(R.id.btn_emp_modify);
        btn_emp_delete = findViewById(R.id.btn_emp_delete);
        btn_emp_close = findViewById(R.id.btn_emp_close);
        MainActivity.container_state = 2;



        Intent intent = getIntent();
        tv_employee_id_detail.setText(intent.getIntExtra("employee_id",0)+"");
        tv_employee_name_detail.setText(intent.getStringExtra("name"));

        if(intent.getStringExtra("admin").equals("Y")){
            rdg_emp_admin.check(R.id.emp_admin_Y);
        }else {
            rdg_emp_admin.check(R.id.emp_admin_N);
        }
        if(intent.getStringExtra("pattern").equals("H101")){
            rdg_emp_pattern.check(R.id.emp_pattern_H101);
        }else{
            rdg_emp_pattern.check(R.id.emp_pattern_H102);
        }
        employee_id = intent.getIntExtra("employee_id",0);

        department_id = intent.getIntExtra("department_id",0);
        spinner_emp_department.setText(intent.getStringExtra("department_name"));
        company_cd = intent.getStringExtra("company");
        spinner_emp_company.setText(intent.getStringExtra("company_name"));
        position = intent.getStringExtra("position");
        spinner_emp_position.setText(intent.getStringExtra("position_name"));

        CommonAskTask askTask_department = new CommonAskTask("andEmpListDepartment.hr", this);
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
        CommonAskTask askTask_company = new CommonAskTask("andEmpListCompany.hr", this);
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
        CommonAskTask askTask_position = new CommonAskTask("andEmpListPosition.hr", this);
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


        spinner_emp_department.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.emp_drop_down_item, department_list_real ));
        spinner_emp_company.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.emp_drop_down_item, company_list_real ));
        spinner_emp_position.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.emp_drop_down_item, position_list_real ));

        spinner_emp_department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                department_id = department_list.get(i).getDepartment_id();
            }
        });

        spinner_emp_company.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                company_cd = company_list.get(i).getCompany_cd();
            }
        });

        spinner_emp_position.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                position = position_list.get(i).getPosition();
            }
        });








        btn_emp_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmpAndInsertDTO dto = new EmpAndInsertDTO();
                dto.setEmployee_id(intent.getIntExtra("employee_id", 0));
                dto.setDepartment_id(department_id);
                dto.setCompany_cd(company_cd);
                dto.setPosition(position);
                dto.setAdmin(rdg_emp_admin.getCheckedRadioButtonId() == R.id.emp_admin_Y ? "Y" : "N");
                dto.setEmployee_pattern(rdg_emp_pattern.getCheckedRadioButtonId() == R.id.emp_pattern_H101 ? "H101" : "H102");

                CommonAskTask askTask = new CommonAskTask("andModifyEmployee.hr", EmpDetailActivity.this);
                askTask.addParam("dto", new Gson().toJson(dto));
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (data.equals("1")){
                            Toast.makeText(EmpDetailActivity.this, "사원 정보가 변경 되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(EmpDetailActivity.this, "사원 등록 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btn_emp_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andDeleteEmployee.hr", EmpDetailActivity.this);
                askTask.addParam("employee_id",employee_id);
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {

                            finish();

                    }
                });
            }
        });

        btn_emp_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }


}