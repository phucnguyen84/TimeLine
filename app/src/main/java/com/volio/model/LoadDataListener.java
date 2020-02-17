package com.volio.model;

import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Datum2;
import com.volio.model.entity3.Example3;
import com.volio.model.entity4.Datum3;
import com.volio.model.entity4.Example4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoadDataListener {
    void onLoadDataSuccess(List<Datum> datas);

    void onLoadCommentData(List<Datum2> datas);

    void onLoadReplyCommentData(List<Datum3> datas);

    void onLoadDataFailure(String message);

    @POST("api/auth/login")
    Call<Example> getCurrentUserData(@Body DataEntered dataEntered);

    @GET("api/diary/get_by_season/{seasonId}")
    Call<Example2> getCurrentData(@Path("seasonId") String seasonId, @Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);

    @GET("api/comment/getList/5/{refer_id}")
    Call<Example3> getCurrentCommentData(@Path("refer_id") String refer_id, @Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);

    @GET("api/comment/getListByParent/{parent_id}")
    Call<Example4> getReplyCommentData(@Path("parent_id") String parent_id, @Query("token") String token);
}
