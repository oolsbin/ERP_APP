package com.example.berp_and.apply;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;


public class MyApplyListFragment extends Fragment {
    Button btn_MyApply;
    EditText edt_rec_name, edt_rec_pw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_my_apply_list, container, false);
        MainActivity.container_state = 5;
        MainActivity.toolbar.setTitle("합격확인");

        btn_MyApply = v.findViewById(R.id.btn_MyApply);
        edt_rec_name = v.findViewById(R.id.edt_rec_name);
        edt_rec_pw = v.findViewById(R.id.edt_rec_pw);








        btn_MyApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyApplyInfoFragment(edt_rec_name.getText()+"", edt_rec_pw.getText()+"")).commit();

            }
        });
        return v;
    }


}