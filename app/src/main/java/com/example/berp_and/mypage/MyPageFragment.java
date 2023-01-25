package com.example.berp_and.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.home.HomeLoginFragment;
import com.example.berp_and.login.LoginActivity;


public class MyPageFragment extends Fragment {
    TextView my_page_name;
    EditText my_page_pw, my_page_pw_ck, my_page_phone, my_page_email;
    Button my_page_submit, my_page_cancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.container_state = 1;
        View v =inflater.inflate(R.layout.fragment_my_page, container, false);

        my_page_name = v.findViewById(R.id.my_page_name);
        my_page_pw = v.findViewById(R.id.my_page_pw);
        my_page_pw_ck = v.findViewById(R.id.my_page_pw_ck);
        my_page_phone = v.findViewById(R.id.my_page_phone);
        my_page_email = v.findViewById(R.id.my_page_email);
        my_page_submit = v.findViewById(R.id.my_page_submit);
        my_page_cancel = v.findViewById(R.id.my_page_cancel);

        MainActivity.toolbar.setTitle("개인정보 수정");
        my_page_name.setText(LoginActivity.loginInfoList.get(0).getName()+"님 정보변경");
        my_page_pw.setText(LoginActivity.loginInfoList.get(0).getPw());
        my_page_phone.setText(LoginActivity.loginInfoList.get(0).getPhone());
        my_page_email.setText(LoginActivity.loginInfoList.get(0).getEmail());

        my_page_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((my_page_pw.getText()+"").equals(my_page_pw_ck.getText()+"")) {
                    CommonAskTask askTask = new CommonAskTask("andModify.mp", getActivity());

                    askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
                    askTask.addParam("phone", my_page_phone.getText() + "");
                    askTask.addParam("pw", my_page_pw.getText() + "");
                    //추가
                    askTask.addParam("email", my_page_email.getText() + "");
                    askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            Toast.makeText(getActivity(), "개인정보 변경 완료", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeLoginFragment()).commit();

                        }

                    });


                } else {
                    Toast.makeText(getActivity(), " 실패", Toast.LENGTH_SHORT).show();

                }
            }
        });

        my_page_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeLoginFragment()).commit();
            }
        });





        return v;
    }
}