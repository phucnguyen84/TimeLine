package com.volio.model;

import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Datum2;
import com.volio.model.entity3.Example3;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoadDataListener {
    void onLoadDataSuccess(ArrayList<Datum> datas);

    void onLoadCommentData(ArrayList<Datum2> data);

    void onLoadDataFailure(String message);

    @POST("api/auth/login")
    Call<Example> getCurrentUserData(@Body DataEntered dataEntered);

    @GET("api/diary/get_by_season/127?")
    Call<Example2> getCurrentData(@Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);

    @GET("api/comment/getList/5/")
    Call<Example3> getCurrentCommentData(@Query("refer_id") String refer_id,@Query("pageLimit") String pageLimit,@Query("page") String page,@Query("token") String token);
}
