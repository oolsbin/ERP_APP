package com.example.berp_and.code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.work.CommonCodeVO;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeModifyActivity extends AppCompatActivity {

    EditText code_modify_used, code_modify_name;
    TextView code_modify_title, code_modify_value, code_modify_maker;
    Button btn_modify_save, btn_modify_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_modify);

        MainActivity.container_state = 2;

        code_modify_title = findViewById(R.id.code_modify_title);
        code_modify_value = findViewById(R.id.code_modify_value);
        code_modify_used = findViewById(R.id.code_modify_used);
        code_modify_name = findViewById(R.id.code_modify_name);
        code_modify_maker = findViewById(R.id.code_modify_maker);

        btn_modify_save = findViewById(R.id.btn_modify_save);
        btn_modify_cancel = findViewById(R.id.btn_modify_cancel);

        CommonCodeVO vo = (CommonCodeVO) getIntent().getSerializableExtra("vo");

        code_modify_title.setText(vo.getCode_comment());
        code_modify_value.setText(vo.getCode_value());
        code_modify_used.setText(vo.getCode_used());
        code_modify_name.setText(vo.getCode_name());
        code_modify_maker.setText(vo.getCode_maker_name());

        btn_modify_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andCodeModify", CodeModifyActivity.this);
                vo.setCode_comment(code_modify_title.getText()+"");
                vo.setCode_value(code_modify_value.getText()+"");
                vo.setCode_used(code_modify_used.getText()+"");
                vo.setCode_name(code_modify_name.getText()+"");
                vo.setCode_maker(LoginActivity.loginInfoList.get(0).getEmployee_id());


                askTask.addParam("vo", new Gson().toJson(vo));
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("TAG", "onResult: "+ vo +"");
                        finish();
                    }
                });
            }
        });
        btn_modify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
