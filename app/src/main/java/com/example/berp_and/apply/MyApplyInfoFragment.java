package com.example.berp_and.apply;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class MyApplyInfoFragment extends Fragment {
    ImageView img_apply_img;
    TextView tv_my_rec_name, tv_my_rec_email, tv_my_rec_phone;
    String name, pw;
    RecyclerView recv_apply_myList;

    public MyApplyInfoFragment(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_apply_info, container, false);

        img_apply_img = v.findViewById(R.id.img_apply_img);
        tv_my_rec_name = v.findViewById(R.id.tv_my_rec_name);
        tv_my_rec_email = v.findViewById(R.id.tv_my_rec_email);
        tv_my_rec_phone = v.findViewById(R.id.tv_my_rec_phone);
        recv_apply_myList = v.findViewById(R.id.recv_apply_myList);



        CommonAskTask askTask = new CommonAskTask("andMyApplyList.rec", getActivity());
        askTask.addParam("name", name);
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<ApplyVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ApplyVO>>() {
                }.getType());

                tv_my_rec_name.setText(name);
                tv_my_rec_email.setText(list.get(0).getApply_email());
                tv_my_rec_phone.setText(list.get(0).getApply_phone());

                Glide.with(getContext()).load(list.get(0).getApply_pic_path()).into(img_apply_img);

                recv_apply_myList.setAdapter(new MyApplyInfoAdapter(getLayoutInflater(), list));
                recv_apply_myList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });


        return v;
    }
}