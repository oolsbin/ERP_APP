package com.example.berp_and.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TempModifyActivity extends AppCompatActivity {
        EditText modify_temp_title, modify_temp_content;
        TextView modify_temp_date,modify_temp_writer;
        Button temp_btn_cancel, temp_btn_modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_modify);
        MainActivity.container_state = 2;

        modify_temp_title = findViewById(R.id.modify_temp_title);
        modify_temp_content = findViewById(R.id.modify_temp_content);
        temp_btn_cancel = findViewById(R.id.temp_btn_cancel);
        temp_btn_modify = findViewById(R.id.temp_btn_modify);
        modify_temp_date = findViewById(R.id.modify_temp_date);
        modify_temp_writer = findViewById(R.id.modify_temp_writer);

        Ing_tableVO vo = (Ing_tableVO) getIntent().getSerializableExtra("vo");

        modify_temp_title.setText(vo.getDocument_title());
        modify_temp_content.setText(vo.getDocument_content());
        modify_temp_writer.setText(vo.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd, yyyy", Locale.KOREA);
        modify_temp_date.setText(sdf.format(new Date()));

        temp_btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonAskTask askTask = new CommonAskTask("andTempModify",TempModifyActivity.this);
                vo.setDocument_content(modify_temp_content.getText()+"");
                vo.setDocument_title(modify_temp_title.getText()+"");

                askTask.addParam("vo", new Gson().toJson(vo));
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("TAG", "onResult: ");
                        finish();
                    }
                });
            }
        });
        temp_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }


}