package com.example.berp_and;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.adminApply.ApplyCheckFragment;
import com.example.berp_and.adminApply.ApplyPassFragment;
import com.example.berp_and.apply.ApplyListFragment;
import com.example.berp_and.apply.MyApplyListFragment;

import com.example.berp_and.code.CodeFragment;
import com.example.berp_and.companyinfo.CompanyInfoFragment;

import com.example.berp_and.approval.ApprovalFragment;
import com.example.berp_and.approval.RecBoxFragment;
import com.example.berp_and.approval.TempBoxFragment;
import com.example.berp_and.approval.WriteBoxFragment;
import com.example.berp_and.emp.EmpFragment;
import com.example.berp_and.emp.EmpInsertFragment;
import com.example.berp_and.home.HomeFragment;
import com.example.berp_and.home.HomeLoginFragment;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.main_menu.MainDTO;
import com.example.berp_and.main_menu.MainPageFragment;
import com.example.berp_and.mypage.MyPageFragment;
import com.example.berp_and.notice.NoticeListFragment;
import com.example.berp_and.salary.BonusListFragment;
import com.example.berp_and.salary.MySalaryFragment;
import com.example.berp_and.salary.SalaryListFragment;
import com.example.berp_and.work.HolidayFragment;
import com.example.berp_and.work.WorkFragment;
import com.example.berp_and.work.WorkIndiFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialViewPager mViewPager;
    DrawerLayout drawer;
    NavigationView nav_view;
    public static Toolbar toolbar;
    ArrayList<MainDTO> list = new ArrayList<>();
    TextView tv_login ;
    ImageView imgv_login;
    int temp_LoginInfo = 0;
    public static int LoginInfo = 0;
    public static int container_state = 0;

    private static final String TAG = "MainActivity";
    private static final int NOTIFICATION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();


                       CommonAskTask tokenAsk = new CommonAskTask("tokenAsk", MainActivity.this);
                       tokenAsk.addParam("token", token);
                       tokenAsk.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                           @Override
                           public void onResult(String data, boolean isResult) {

                           }
                       });
                    }
                });
        askNotificationPermission();
        onNewToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(temp_LoginInfo != LoginInfo){
            temp_LoginInfo = LoginInfo;
            initView();
        }

    }

    public boolean isLogin(){
        if(LoginInfo==1){
            list = afterLoginMenu();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeLoginFragment()).commit();
            return true;
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }
        list = beforeLoginMenu();
        return false;
    }

    public void initView(){
        toolbar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        mViewPager = findViewById(R.id.materialViewPager);


        tv_login = mViewPager.findViewById(R.id.tv_login);
        imgv_login = mViewPager.findViewById(R.id.imgv_login);


        if(isLogin()){

            tv_login.setText(LoginActivity.loginInfoList.get(0).getName()+"님 반갑습니다.");
            imgv_login.setImageResource(R.drawable.ic_main_menu_logout);
        }else{
            tv_login.setText("로그인을 하셔야 전체 메뉴가 보입니다.");
            imgv_login.setImageResource(R.drawable.ic_main_menu_login);
        }

        tv_login.setOnClickListener(this);
        imgv_login.setOnClickListener(this);

        toolbar.setTitle("YmNetWork");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close


        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        mViewPager.getToolbar().setTitle("YmNetWork");
        mViewPager.getToolbar().setTitleTextColor(Color.parseColor("#FFFFFF"));

        mViewPager.getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.closeDrawers();
                }
                return true;
            }
        });
        mViewPager.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.closeDrawers();
                }
            }
        });
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

                return new MainPageFragment(list.get(position) ,list.get(position).getSubList() , position);

            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return list.get(position).getMainMenu();
            }
        });
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {

            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green,
                                getResources().getDrawable(R.drawable.company_img));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.red,
                                getResources().getDrawable(R.drawable.menu_bar8));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.menu_bar7));
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.black,
                                getResources().getDrawable(R.drawable.menu_bar4));

                    case 4:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.menu_bar1));
                    case 5:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.purple_500,
                                getResources().getDrawable(R.drawable.menu_bar9));
                    case 6:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.purple_200,
                                getResources().getDrawable(R.drawable.menu_bar5));
                }
                return null;
            }
        });
        mViewPager.getPagerTitleStrip().setTextColor(Color.parseColor("#FFFFFF"));
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());



    }


    public ArrayList<MainDTO> afterLoginMenu(){
        ArrayList<MainDTO> tempList = new ArrayList<>();
        ArrayList<MainDTO> subList1= new ArrayList<>();

        subList1.add(new MainDTO("나의 출퇴근 조회", new WorkIndiFragment()));
        subList1.add(new MainDTO("개인정보 수정", new MyPageFragment()));
        subList1.add(new MainDTO("공지사항", new NoticeListFragment()));

        tempList.add(new MainDTO("마이 페이지" , subList1));

        ArrayList<MainDTO> subList4= new ArrayList<>();
        subList4.add(new MainDTO("휴일 관리", new HolidayFragment()));
        subList4.add(new MainDTO("근무시간관리", new WorkFragment()));

        tempList.add(new MainDTO("근태관리" ,subList4));

        ArrayList<MainDTO> subList6= new ArrayList<>();
        subList6.add(new MainDTO("상신함", new WriteBoxFragment()));
        subList6.add(new MainDTO("수신함", new RecBoxFragment()));
        subList6.add(new MainDTO("임시 보관함", new TempBoxFragment()));
        subList6.add(new MainDTO("결재처리함", new ApprovalFragment()));
        subList6.add(new MainDTO("코드관리", new CodeFragment()));

        tempList.add(new MainDTO("업무관리" ,subList6));


        ArrayList<MainDTO> subList5= new ArrayList<>();
        subList5.add(new MainDTO("나의 급여 조회", new MySalaryFragment()));
        subList5.add(new MainDTO("급여관리", new SalaryListFragment()));
        subList5.add(new MainDTO("상여금 현황", new BonusListFragment()));

        tempList.add(new MainDTO("급여관리" ,subList5));

        ArrayList<MainDTO> subList3= new ArrayList<>();
        subList3.add(new MainDTO("사원 추가", new EmpInsertFragment()));
        subList3.add(new MainDTO("사원 관리", new EmpFragment()));

        tempList.add(new MainDTO("인사관리" ,subList3));


        ArrayList<MainDTO> subList2= new ArrayList<>();
        subList2.add(new MainDTO("지원자 목록", new ApplyCheckFragment()));
        subList2.add(new MainDTO("합격자 목록", new ApplyPassFragment()));

        tempList.add(new MainDTO("채용관리" ,subList2));




        return tempList;
    }
    
    //로그인 전
    public ArrayList<MainDTO> beforeLoginMenu(){

        ArrayList<MainDTO> subList1= new ArrayList<>();
        subList1.add(new MainDTO("공지사항 글보기", new NoticeListFragment()));


        ArrayList<MainDTO> subList2= new ArrayList<>();
        subList2.add(new MainDTO("회사 정보 보기", new CompanyInfoFragment()));

        ArrayList<MainDTO> tempList = new ArrayList<>();
        tempList.add(new MainDTO("공지사항" , subList1));
        tempList.add(new MainDTO("회사정보" , subList2));


        ArrayList<MainDTO> subList= new ArrayList<>();
        subList.add(new MainDTO("채용공고보기", new ApplyListFragment()));
        subList.add(new MainDTO("지원여부확인", new MyApplyListFragment()));
        tempList.add(new MainDTO("채용정보" , subList));



        return tempList;
    }

    private long backPressedTime= 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(drawer.isDrawerOpen(Gravity.LEFT)){
            drawer.closeDrawers();
        }else{
            if(System.currentTimeMillis() - backPressedTime >= 2000) {
                backPressedTime = System.currentTimeMillis();
               // Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
        }


        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :
                if (container_state == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeLoginFragment()).commit();
                    container_state = 0;
                    return true;
                }else if(container_state == 5){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                    container_state = 4;
                    return true;
                }
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if(LoginInfo==0 && v.getId() == R.id.tv_login || v.getId() == R.id.imgv_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            if(drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.closeDrawers();
            }
        }else{
            LoginActivity.loginInfoList.clear();
            LoginInfo = 0 ;
            if(drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.closeDrawers();

            }
        }
    }


    public  void changeFragment(Fragment fragment){

        if(drawer.isDrawerOpen(Gravity.LEFT)){
            drawer.closeDrawers();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();

    }


    public void onNewToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, token);
                     //   Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
//        if (Build.VERSION.SDK_INT >= 33) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
//                    PackageManager.PERMISSION_GRANTED) {
//                // FCM SDK (and your app) can post notifications.
//            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
//                // TODO: display an educational UI explaining to the user the features that will be enabled
//                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
//                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
//                //       If the user selects "No thanks," allow the user to continue without notifications.
//            } else {
//                // Directly ask for the permission
//                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
//            }
//        }
    }


}

