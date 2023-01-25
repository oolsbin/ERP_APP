package com.example.berp_and.approval;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;


public class WriteCancelPopFragment extends BottomSheetDialogFragment implements View.OnClickListener  {
    Button write_cancel_ok, write_cancel_no;

    And_Ing_tableVO insert_vo;

    public WriteCancelPopFragment(And_Ing_tableVO insert_vo) {
        this.insert_vo = insert_vo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_write_cancel_pop, container, false);

        write_cancel_ok = v.findViewById(R.id.write_cancel_ok);
        write_cancel_no = v.findViewById(R.id.write_cancel_no);


        write_cancel_ok.setOnClickListener(this);
        write_cancel_no.setOnClickListener(this);


        return v;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.write_cancel_ok){
            CommonAskTask askTask = new CommonAskTask("andNewNotInsert.ap", getActivity());
            askTask.addParam("vo", new Gson().toJson(insert_vo));
            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {

                    Toast.makeText(getActivity(), "임시 등록", Toast.LENGTH_SHORT).show();

                }
            });

        }else if(v.getId()== R.id.write_cancel_no){

        }
    }
}