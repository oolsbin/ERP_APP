package com.example.berp_and.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

public class ApprovalDetailActivity extends AppCompatActivity {
    TextView approval_detail_title,approval_detail_writer,approval_detail_date,approval_detail_content,approval_detail_approver,approval_status,approval_finish_date
            ;
    Result_tableVO vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_detail);
        MainActivity.container_state = 2;


        approval_detail_title = findViewById(R.id.approval_detail_title);
        approval_detail_content = findViewById(R.id.approval_detail_content);
        approval_detail_writer = findViewById(R.id.approval_detail_writer);
        approval_detail_date = findViewById(R.id.approval_detail_date);
        approval_detail_approver = findViewById(R.id.approval_detail_approver);
        approval_status = findViewById(R.id.approval_status);
        approval_finish_date = findViewById(R.id.approval_finish_date);

        vo = (Result_tableVO) getIntent().getSerializableExtra("vo");

        if(vo.getFinish_date() == null){
            approval_finish_date.setText("미결재");
        }else {
            approval_finish_date.setText("결재 완료 : " + vo.getFinish_date()+"");
        }


        approval_detail_content.setText(vo.getDocument_content());
        approval_detail_title.setText(vo.getDocument_title());
        approval_detail_writer.setText(vo.getDrafter()+"/"+vo.getDrafter_position());
        approval_status.setText("처리상태 : " + vo.getC_status());
        approval_detail_date.setText(vo.getDocument_date()+"");
        approval_detail_approver.setText("결재자 : "  + vo.getApprover()+"/"+vo.getApprover_position() +"");




    }
}