package com.example.berp_and.apply;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpListAdapter;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ApplyListFragment extends Fragment {

    RecyclerView recv_apply_board;
    ArrayList<RecruitVO> rec_list;
    AutoCompleteTextView apply_item_filled_exposed;
    ArrayList<RecruitVO> list;
    ArrayList<String> list_real;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_apply_list, container, false);
        MainActivity.container_state = 5;
        MainActivity.toolbar.setTitle("지원하기");

        recv_apply_board = v.findViewById(R.id.recv_apply_board);
        apply_item_filled_exposed = v.findViewById(R.id.apply_item_filled_exposed);

        rec_list_select();

        CommonAskTask askTask = new CommonAskTask("andApplySpinnerList.rec", getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
               list = new Gson().fromJson(data, new TypeToken<ArrayList<RecruitVO>>() {
                }.getType());

                list_real = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    list_real.add(list.get(i).getCareer_name());
                }

                apply_item_filled_exposed.setText("직종선택");

                apply_item_filled_exposed.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        list_real
                ));
            }
        });

        apply_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                CommonAskTask askTask_selectList = new CommonAskTask("andApplyCareerSelect.rec", getContext());
                askTask_selectList.addParam("career", list.get(i).getCareer());
                askTask_selectList.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        ArrayList<RecruitVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<RecruitVO>>() {
                        }.getType());

                        recv_apply_board.setAdapter(new ApplyListAdapter(getLayoutInflater(),list,getContext()));
                        recv_apply_board.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                    }
                });
            }
        });




        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.container_state = 5;
    }

    public void rec_list_select(){
        CommonAskTask askTask = new CommonAskTask("andRecList.rec", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                rec_list = new Gson().fromJson(data, new TypeToken<ArrayList<RecruitVO>>() {
                }.getType());
                recv_apply_board.setAdapter(new ApplyListAdapter(getLayoutInflater(), rec_list, getContext()));
                recv_apply_board.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            }
        });
    }
}