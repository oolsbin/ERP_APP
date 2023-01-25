package com.example.berp_and.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.home.EmpVO;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.login.LoginMemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MyPageActivity extends AppCompatActivity {

    TextView my_page_name;
    EditText my_page_pw, my_page_pw_ck, my_page_phone, my_page_email;
    Button my_page_submit, my_page_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Intent intent = getIntent();
        LoginMemberVO vo = (LoginMemberVO) intent.getSerializableExtra("vo");

        my_page_name = findViewById(R.id.my_page_name);
        my_page_pw = findViewById(R.id.my_page_pw);
        my_page_pw_ck = findViewById(R.id.my_page_pw_ck);
        my_page_phone = findViewById(R.id.my_page_phone);
        my_page_email = findViewById(R.id.my_page_email);
        my_page_submit = findViewById(R.id.my_page_submit);
        my_page_cancel = findViewById(R.id.my_page_cancel);


        my_page_name.setText(LoginActivity.loginInfoList.get(0).getName()+"님 정보변경");
        my_page_pw.setText(LoginActivity.loginInfoList.get(0).getPw());
        my_page_phone.setText(LoginActivity.loginInfoList.get(0).getPhone());
        my_page_email.setText(LoginActivity.loginInfoList.get(0).getEmail());


 /*       vo.setPhone(my_page_phone.getText()+"");
        vo.setPw(my_page_pw.getText()+"");
        vo.setEmail(my_page_email.getText()+"");*/





            my_page_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((my_page_pw.getText()+"").equals(my_page_pw_ck.getText()+"")) {
                        CommonAskTask askTask = new CommonAskTask("andModify.mp", MyPageActivity.this);

                        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
                        askTask.addParam("phone", my_page_phone.getText() + "");
                        askTask.addParam("pw", my_page_pw.getText() + "");
                        //추가
                        askTask.addParam("email", my_page_email.getText() + "");
                        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                            @Override
                            public void onResult(String data, boolean isResult) {
                                Toast.makeText(MyPageActivity.this, "개인정보 변경 완료", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                        });


                    } else {
                        Toast.makeText(MyPageActivity.this, " 실패", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        my_page_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}