package com.example.berp_and.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.work.HolidayInsertFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class WriteNewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_write_title, edt_write_content;
    AutoCompleteTextView spinner_write1, spinner_write2;
    ArrayList<And_Ing_tableVO> dep_list = new ArrayList<>();
    ArrayList<String> str_dep = new ArrayList<>();
    ArrayList<And_Ing_tableVO> name_list = new ArrayList<>();
    ArrayList<String> str_name = new ArrayList<>();
    int app_id;

    Button btn_write_new_insert, btn_write_new_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_new);


        edt_write_title = findViewById(R.id.edt_write_title);
        edt_write_content = findViewById(R.id.edt_write_content);
        spinner_write1 = findViewById(R.id.spinner_write1);
        spinner_write2 = findViewById(R.id.spinner_write2);
        btn_write_new_insert = findViewById(R.id.btn_write_new_insert);
        btn_write_new_cancel = findViewById(R.id.btn_write_new_cancel);

        first_list();

        second_list();

        btn_write_new_insert.setOnClickListener(this);
        btn_write_new_cancel.setOnClickListener(this);



    }

    public void first_list(){
        CommonAskTask firstDep = new CommonAskTask("andFirstDep.ap", WriteNewActivity.this);
        firstDep.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                str_dep.clear();
                dep_list = new Gson().fromJson(data, new TypeToken<ArrayList<And_Ing_tableVO>>() {
                }.getType());
                for (int i = 0; i < dep_list.size(); i++) {
                    str_dep.add(dep_list.get(i).getEmp_dpmt_name());
                }
                spinner_write1.setAdapter(new ArrayAdapter<>(
                        WriteNewActivity.this.getApplicationContext(), R.layout.emp_drop_down_item,
                        str_dep
                ));
            }
        });

    }

    public void second_list(){
        spinner_write1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                CommonAskTask sec_ask = new CommonAskTask("andSecondDep.ap", WriteNewActivity.this);
                sec_ask.addParam("department_id", dep_list.get(i).getDepartment_id());
                sec_ask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        str_name.clear();
                        name_list = new Gson().fromJson(data, new TypeToken<ArrayList<And_Ing_tableVO>>() {
                        }.getType());
                        for (int i = 0; i < name_list.size(); i++) {
                            str_name.add(name_list.get(i).getEmp_name());
                        }
                        spinner_write2.setAdapter(new ArrayAdapter<>(
                                WriteNewActivity.this.getApplicationContext(), R.layout.emp_drop_down_item,
                                str_name
                        ));
                    }
                });

                spinner_write2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        app_id = name_list.get(i).getEmployee_id();
                    }
                });

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_write_new_insert){
            And_Ing_tableVO insert_vo = new And_Ing_tableVO();
            insert_vo.setEmployee_id(LoginActivity.loginInfoList.get(0).getEmployee_id());
            insert_vo.setDepartment_id(LoginActivity.loginInfoList.get(0).getDepartment_id());
            insert_vo.setCompany_cd(LoginActivity.loginInfoList.get(0).getCompany_cd());
            insert_vo.setDocument_title(edt_write_title.getText()+"");
            insert_vo.setDocument_content(edt_write_content.getText()+"");
            insert_vo.setApprover_id(app_id);

            WriteNewpopFragment fragment = new WriteNewpopFragment(insert_vo);
            fragment.show(WriteNewActivity.this.getSupportFragmentManager(),fragment.getTag());
            fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.TransparentBottomSheetDialogFragment
            );


        }else if(v.getId()== R.id.btn_write_new_cancel){
            And_Ing_tableVO insert_vo = new And_Ing_tableVO();
            insert_vo.setEmployee_id(LoginActivity.loginInfoList.get(0).getEmployee_id());
            insert_vo.setDepartment_id(LoginActivity.loginInfoList.get(0).getDepartment_id());
            insert_vo.setCompany_cd(LoginActivity.loginInfoList.get(0).getCompany_cd());
            insert_vo.setDocument_title(edt_write_title.getText()+"");
            insert_vo.setDocument_content(edt_write_content.getText()+"");
            insert_vo.setApprover_id(app_id);
            WriteCancelPopFragment fragment = new WriteCancelPopFragment(insert_vo);
            fragment.show(WriteNewActivity.this.getSupportFragmentManager(),fragment.getTag());
            fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.TransparentBottomSheetDialogFragment
            );
        }
    }


}