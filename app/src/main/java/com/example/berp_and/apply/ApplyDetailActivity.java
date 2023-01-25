package com.example.berp_and.apply;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

public class ApplyDetailActivity extends AppCompatActivity {
    TextView tv_rec_detail_company, tv_rec_detail_title, tv_detail_rec_career, tv_rec_detail_employee_pattern, tv_rec_detail_salary, tv_rec_detail_content;
    Button btn_apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_detail);
        MainActivity.container_state = 6;

        tv_rec_detail_company=findViewById(R.id.tv_rec_detail_company);
        tv_rec_detail_title=findViewById(R.id.tv_rec_detail_title);
        tv_detail_rec_career=findViewById(R.id.tv_detail_rec_career);
        tv_rec_detail_employee_pattern=findViewById(R.id.tv_rec_detail_employee_pattern);
        tv_rec_detail_salary=findViewById(R.id.tv_rec_detail_salary);

        tv_rec_detail_content = findViewById(R.id.tv_rec_detail_content);

        tv_rec_detail_company.setText(getIntent().getStringExtra("company_name"));
        tv_rec_detail_title.setText(getIntent().getStringExtra("recruit_title"));
        tv_detail_rec_career.setText(getIntent().getStringExtra("career_name"));
        tv_rec_detail_employee_pattern.setText(getIntent().getStringExtra("employee_pattern_name"));
        tv_rec_detail_salary.setText("연봉 "+getIntent().getStringExtra("salary")+"만원");
        tv_rec_detail_content.setText(getIntent().getStringExtra("recruit_content"));



    }
}