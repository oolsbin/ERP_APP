package com.example.berp_and.main_menu;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MainDTO {
    String mainMenu ;
    ArrayList<MainDTO> subList;
    int imgRes ;


    public MainDTO(String mainMenu, ArrayList<MainDTO> subList , int imgRes) {
        this.mainMenu = mainMenu;
        this.subList = subList;
        this.imgRes = imgRes;
    }


    public MainDTO(String mainMenu, ArrayList<MainDTO> subList) {
        this.mainMenu = mainMenu;
        this.subList = subList;
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    public ArrayList<MainDTO> getSubList() {
        return subList;
    }

    public void setSubList(ArrayList<MainDTO> subList) {
        this.subList = subList;
    }

    String menu ;
    Fragment fragment;
    Activity activity;

    public MainDTO(String menu, Fragment fragment) {
        this.menu = menu;
        this.fragment = fragment;
    }

    public MainDTO(String menu, Activity activity) {
        this.menu = menu;
        this.activity = activity;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
