package com.example.berp_and.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

import java.util.ArrayList;

public class WriteDetailActivity extends AppCompatActivity {




    AutoCompleteTextView spinner_write1, spinner_write2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_detail);
        MainActivity.container_state = 2;







    }
}