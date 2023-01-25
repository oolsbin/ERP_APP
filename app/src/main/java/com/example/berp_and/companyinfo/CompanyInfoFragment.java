package com.example.berp_and.companyinfo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.MainActivity;
import com.example.berp_and.R;

public class CompanyInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company_info, container, false);
        MainActivity.toolbar.setTitle("회사소개");

        MainActivity.container_state = 5;
        return v;
    }
}