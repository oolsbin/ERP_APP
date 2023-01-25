package com.example.berp_and.adminApply;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

import java.util.ArrayList;

public class ApplyCheckDetailActivity extends AppCompatActivity {
    ImageView img_apply_detail;
    TextView tv_apply_detail_name, tv_apply_detail_email, tv_apply_detail_phone, tv_apply_detail_title, tv_apply_detail_content, tv_apply_detail_company,
            tv_apply_detail_salary, tv_apply_detail_position;
    Button btn_pass, btn_fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_check_detail);
        MainActivity.container_state = 2 ;
        MainActivity.toolbar.setTitle("지원자 상세정보");
        img_apply_detail = findViewById(R.id.img_apply_detail);
        tv_apply_detail_name = findViewById(R.id.tv_apply_detail_name);
        tv_apply_detail_email = findViewById(R.id.tv_apply_detail_email);
        tv_apply_detail_phone = findViewById(R.id.tv_apply_detail_phone);
        tv_apply_detail_title = findViewById(R.id.tv_apply_detail_title);
        tv_apply_detail_content = findViewById(R.id.tv_apply_detail_content);

        tv_apply_detail_salary = findViewById(R.id.tv_apply_detail_salary);
        tv_apply_detail_position = findViewById(R.id.tv_apply_detail_position);
        btn_pass = findViewById(R.id.btn_pass);
        btn_fail = findViewById(R.id.btn_fail);

        ApplyVO vo = (ApplyVO) getIntent().getSerializableExtra("vo");

        Glide.with(this).load(vo.getApply_pic_path()).into(img_apply_detail);

        tv_apply_detail_name.setText(vo.getApply_name());
        tv_apply_detail_email.setText(vo.getApply_email());
        tv_apply_detail_phone.setText(vo.getApply_phone());
        tv_apply_detail_title.setText(vo.getRecruit_title());
        tv_apply_detail_content.setText(vo.getRecruit_content());

        tv_apply_detail_salary.setText("[연봉] " + vo.getSalary() + "만원");
        tv_apply_detail_position.setText(vo.getCareer_name()+ " / " + vo.getEmployee_pattern_name());

        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andApplyPassORFail.rec", ApplyCheckDetailActivity.this);
                askTask.addParam("phone", vo.getApply_phone());
                askTask.addParam("check","Y");
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        finish();
                        Toast.makeText(ApplyCheckDetailActivity.this, "합격처리 됐습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btn_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andApplyPassORFail.rec", ApplyCheckDetailActivity.this);
                askTask.addParam("phone", vo.getApply_phone());
                askTask.addParam("check","X");
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        finish();
                        Toast.makeText(ApplyCheckDetailActivity.this, "불합격처리 됐습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}