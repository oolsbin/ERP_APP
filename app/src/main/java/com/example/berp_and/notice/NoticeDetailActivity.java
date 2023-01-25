package com.example.berp_and.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.adminApply.ApplyVO;

public class NoticeDetailActivity extends AppCompatActivity {
    TextView notice_detail_title,notice_detail_writer,notice_detail_date, notice_detail_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        MainActivity.container_state= 2;

        notice_detail_content = findViewById(R.id.notice_detail_content);
        notice_detail_title = findViewById(R.id.notice_detail_title);
        notice_detail_writer = findViewById(R.id.notice_detail_writer);
        notice_detail_date = findViewById(R.id.notice_detail_date);

        NoticeVO vo = (NoticeVO) getIntent().getSerializableExtra("vo");


        notice_detail_content.setText(vo.getNotice_content());
        notice_detail_title.setText(vo.getNotice_title());
        notice_detail_writer.setText(vo.getNotice_writer());
        notice_detail_date.setText(vo.getNotice_date());

        

    }
}