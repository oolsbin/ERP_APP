package com.example.berp_and.code;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.work.CommonCodeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CodeFragment extends Fragment {
    RecyclerView recv_code;
    ArrayList<CommonCodeVO> code_list = new ArrayList<>();

    AutoCompleteTextView code_check_spinner;
    ArrayList<CommonCodeVO> list;
    ArrayList<String> list_real;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_code, container, false);
        MainActivity.container_state = 1;

        recv_code = v.findViewById(R.id.recv_code);
        code_check_spinner = v.findViewById(R.id.code_check_spinner);

        rec_code_select();

        CommonAskTask askTask = new CommonAskTask("andCodeSpinnerList.rec", getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<CommonCodeVO>>() {
                }.getType());

                list_real = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    list_real.add(list.get(i).getCode_comment());
                }

                code_check_spinner.setText("인사코드");
                code_check_spinner.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.code_drop_down_item, list_real));
            }
        });

        code_check_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                CommonAskTask askTask_selectList = new CommonAskTask("andCodeValueSelect.rec", getContext());
                askTask_selectList.addParam("title", list.get(i).getCode_comment());
                askTask_selectList.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        ArrayList<CommonCodeVO> list2 = new Gson().fromJson(data, new TypeToken<ArrayList<CommonCodeVO>>() {
                        }.getType());

                        recv_code.setAdapter(new CodeAdapter(getLayoutInflater(), list2, getContext()));
                        recv_code.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


                    }
                });
            }
        });

        return v;
    }

    public void onResume() {
        super.onResume();
      //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CodeFragment() ).commit();
        MainActivity.container_state = 1;
        rec_code_select();

    }

    //레트로핏 시작
    public void rec_code_select() {
        CommonAskTask task = new CommonAskTask("andCodeList.rec", getContext());//통신처리

        task.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult) {

                    Log.d("로그", "onResult: " + data);
                    code_list = new Gson().fromJson(data, new TypeToken<ArrayList<CommonCodeVO>>() {
                    }.getType());

                    CodeAdapter adapter = new CodeAdapter(getLayoutInflater(), code_list, getContext());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext(), RecyclerView.VERTICAL, false);
                    recv_code.setAdapter(adapter);
                    recv_code.setLayoutManager(manager);

                }else {
                    Log.d("실패", "onResult: "+data);
                }
            }
        });
    }
}