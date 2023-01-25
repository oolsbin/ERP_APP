package com.example.berp_and.approval;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.notice.NoticeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;

public class TempDetailActivity extends AppCompatActivity {
    TextView temp_detail_date,temp_detail_title,temp_detail_content,temp_detail_writer;
    Button temp_btn_submit,temp_btn_delete,temp_btn_modify;
    Ing_tableVO vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_detail);
        MainActivity.container_state = 2;


        temp_detail_title = findViewById(R.id.temp_detail_title);
        temp_detail_content = findViewById(R.id.temp_detail_content);
        temp_detail_writer = findViewById(R.id.temp_detail_writer);
        temp_detail_date = findViewById(R.id.temp_detail_date);
        temp_btn_submit = findViewById(R.id.temp_btn_submit);
        temp_btn_delete = findViewById(R.id.temp_btn_delete);
        temp_btn_modify = findViewById(R.id.temp_btn_modify);

        vo = (Ing_tableVO) getIntent().getSerializableExtra("vo");


        temp_detail_content.setText(vo.getDocument_content());
        temp_detail_title.setText(vo.getDocument_title());
        temp_detail_writer.setText(vo.getEmployee_name());
        temp_detail_date.setText(vo.getDocument_date()+"");



        temp_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andTempSubmit",TempDetailActivity.this);
                askTask.addParam("ing_no",vo.getIng_no());
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        finish();
                    }
                });
            }
        });
        temp_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andTempDelete",TempDetailActivity.this);
                askTask.addParam("ing_no",vo.getIng_no());
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        finish();
                    }
                });
            }
        });
        temp_btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TempDetailActivity.this,TempModifyActivity.class);

                intent.putExtra("vo", (Serializable) vo);
                startActivityForResult(intent , 1000);

                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            origin_list();
        }
    }

    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andTempListOne", TempDetailActivity.this);
        askTask.addParam("ing_no", vo.getIng_no());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Ing_tableVO dto = new Gson().fromJson(data, Ing_tableVO.class);

                temp_detail_content.setText(dto.getDocument_content());
                temp_detail_title.setText(dto.getDocument_title());
                temp_detail_writer.setText(dto.getEmployee_name());
                temp_detail_date.setText(dto.getDocument_date()+"");




            }
        });
    }
}