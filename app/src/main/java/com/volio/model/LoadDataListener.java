package com.volio.model;

import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoadDataListener {
    void onLoadDataSuccess(ArrayList<Datum> datas);
    void onLoadDataFailure(String message);

    @POST("api/auth/login")
    Call<Example> getCurrentUserData(@Body DataEntered dataEntered);

    @GET("api/diary/get_by_season/127?")
    Call<Example2> getCurrentData(@Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);
}
