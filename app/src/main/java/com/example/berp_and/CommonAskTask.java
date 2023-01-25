package com.example.berp_and;

//프로그램은 크게 두가지의 작업영역을 가진다.
// FRONT (View) : 사람의 눈에 보이는 영역 디자인, 효과
// BACKGROUND (BACK) : 사람의 눈에 보이지 않지만 실제로 데이터가 이동하는 처리
// 메인 쓰레드 : 사용자가 조작하는 영역의 작업공간
// => 네트워크에 접속해서 (스프링) 데이터를 가져와야하는데 여러가지 이유로 응답이 없을때,
// => 사용자가 앱을 사용하면서 비동기로 작업이 되어야 할대 등등
// BackGround Task : 디자인은 안멈춘 상태에서 어떤 작업을 비동기로 하는 것.


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;

public class CommonAskTask extends AsyncTask<String, String, String> {
    String url ;
    HashMap<String, Object> params = new HashMap<>();
    Context context;
    ProgressDialog dialog;
    public AsynkTaskCallback callback;


    public CommonAskTask(String url, Context context) {
        this.url = url; //Spring에 있는 여러 mapping에 접근
        this.context = context; //progressDialog 보여주려고
        this.params = new HashMap<>();
        this.dialog = new ProgressDialog(context);
    }
    public void addParam(String key, Object value){
        this.params.put(key, value);
    }

    public void executeAsk(AsynkTaskCallback callback){
        this.callback = callback;
        this.execute(); //실행부
    }


    @Override
    protected void onPreExecute() {
       if(dialog == null)return;
       dialog.setProgress(ProgressDialog.STYLE_SPINNER);
       dialog.setMessage("스프링으로 데이터 가져오는 중");
       dialog.setCancelable(false);
       dialog.show();
    }

    //안드로이드 화면에 안보이는 영역에서 할 작업을 미리 넣어둠.
    //excute라는 명령에 의해서 실행되는 분
    @Override
    protected String doInBackground(String... strings) {
        String rtnData =null;
        com.example.berp_and.ApiInterface apiInterface = com.example.berp_and.ApiClient.getApiClient().create(com.example.berp_and.ApiInterface.class);
       //enque < 작업을 비동기로 작업해줌, 불필여한 매소드 구간이 많이생김
        try {
            //재사용을 하기 위해서는 url과 param이 고정되면 안됨,'
            //list.hr 조회하기 위해서 파라메터 사원 코드정보를 파라메터로 씀
            
            
            rtnData = apiInterface.getData(url, params).execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rtnData;
    }
    //excute명령이 전부 끝나고 나서 실행됨.
    @Override
    protected void onPostExecute(String rtnData) {
        if(dialog != null) dialog.dismiss();
        if(rtnData == null || rtnData.length() == 0 ){
            callback.onResult(rtnData, false);
        }else {
            callback.onResult(rtnData,true);
        }

    }
    //콜백을 위한 인터페이스 정의.
    public interface AsynkTaskCallback{
        void onResult(String data, boolean isResult);
    }



}
