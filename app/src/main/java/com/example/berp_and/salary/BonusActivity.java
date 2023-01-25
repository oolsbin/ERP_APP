package com.example.berp_and.salary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BonusActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView tv_bonus_date, tv_name_bonus;
    Button bonusBtn_save, bonusBtn_cancel;
    EditText tv_bonus, tv_comment;
    int employee_id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        MainActivity.container_state = 2;

        tv_bonus_date = findViewById(R.id.tv_bonus_date);
        tv_bonus = findViewById(R.id.tv_bonus);
        tv_comment = findViewById(R.id.tv_comment);

        tv_name_bonus = findViewById(R.id.tv_name_bonus);

        bonusBtn_save = findViewById(R.id.bonusBtn_save);
        bonusBtn_cancel = findViewById(R.id.bonusBtn_cancel);

        Intent intent = getIntent();
        SalaryVO vo = (SalaryVO) getIntent().getSerializableExtra("vo");

        tv_name_bonus.setText(vo.getDepartment_name()+" "+vo.getC_position()+" "+vo.getName());

        ImageView datePicker = findViewById(R.id.btn_datePicker);

        bonusBtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask("andInsertBonus.sa", BonusActivity.this);
                askTask.addParam("employee_id", vo.getEmployee_id());
                askTask.addParam("bonus", tv_bonus.getText());
                askTask.addParam("bonus_comment", tv_comment.getText());
                askTask.addParam("bonus_date", c.getTime());
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult){

                        }else{
                            Log.d("로그", "onResult: 통신 실패");
                        }
                        finish();
                    }//public void onResult(String data, boolean isResult)
                });
            }//public void onClick(View v)
        });//h.bonusBtn_save.setOnClickListener(new View.OnClickListener()


        bonusBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



        ImageView btn_datePicker = findViewById(R.id.btn_datePicker);
        btn_datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

    }//onCreate


    Calendar c;

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //Calendar c = Calendar.getInstance();

        c = Calendar.getInstance();
        c.set(year,month,dayOfMonth);

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //String currentDate = sdf.format(c.getTime());

        String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime());

        TextView tv_bonus_date = (TextView) findViewById(R.id.tv_bonus_date);
        tv_bonus_date.setText(currentDate);
    }
}//BonusActivity










//                h.bonusBtn_save.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("bonus" , h.tv_bonus);
//                        map.put("bonus_date" , h.tv_bonus_date);
//                        map.put("bonus_comment", h.tv_comment);
//                        map.put("employee_id", h.tv_employee_id);
//
//                        apiInterface.getData("andInsertBonus.sa", map).enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                Log.d("로그", "onResponse: " + response.body());
//
//                            }//onResponse
//
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//                            }
//                        }); //apiInterface.getData("andLogin" ,map ).enqueue(new Callback<String>()
//                    }//onClick
//                });//h.bonusBtn_save.setOnClickListener(new View.OnClickListener()