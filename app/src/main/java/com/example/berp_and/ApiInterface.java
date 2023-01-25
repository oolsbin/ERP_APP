package com.example.berp_and;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {
    //BaseUrl 외에 다른 맵핑들을 요청하기위한 설계도.
    //(localhost/middle <- BaseUrl + Mapping)
    //list.cu, list.jr 등등


    @FormUrlEncoded //String으로 맵핑을 받겠다.
    @POST
    Call<String> getData(@Url String url, @FieldMap HashMap<String,Object> params);


    @GET("{url}")    //BASEURL + URL(맵핑) + ? 뒤에 나오는 파라메터
    Call<String> getGoData(@Path("url") String url, @QueryMap HashMap<String, String> params);


    @POST("file.f")
    @Multipart
    Call<String> sendFile(@Part MultipartBody.Part file); //파일 전송을 함



}
