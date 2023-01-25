package com.example.berp_and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.berp_and.emp.EmpVO;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.login.LoginMemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class QRscanner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        new IntentIntegrator(this).initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        try {
            EmpVO list = new Gson().fromJson(result.getContents(), new TypeToken<EmpVO>() {
            }.getType());


            Log.d("왜", "onActivityResult: "+list.getEmployee_id());
            Log.d("왜", "onActivityResult: "+list.getPw());

            CommonAskTask askTask = new CommonAskTask("andLogin.mem", this);
            askTask.addParam("id", Integer.parseInt(list.getEmployee_id()+""));
            askTask.addParam("pw", list.getPw());
            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {


                    LoginActivity.loginInfoList = new Gson().fromJson(data, new TypeToken<ArrayList<LoginMemberVO>>() {
                    }.getType());

                    MainActivity.LoginInfo = 1;

                    finish();

                }
            });
        }catch (Exception e){
            Toast.makeText(QRscanner.this, "잘못된 QR코드입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

}