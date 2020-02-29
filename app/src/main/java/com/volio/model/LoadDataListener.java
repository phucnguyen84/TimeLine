package com.volio.model;

import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Datum2;
import com.volio.model.entity3.Example3;
import com.volio.model.entity4.Example4;
import com.volio.model.entity4.Datum3;
import com.volio.model.entityLiked.Data;
import com.volio.model.entityLiked.DataLiked;
import com.volio.model.entityLiked.DataPostLiked;
import com.volio.model.entityPeopleLiked.DataPeopleLiked;
import com.volio.model.entityPeopleLiked.Datum4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoadDataListener {
    void onLoadDataSuccess(List<Datum> datas);

    void onLoadDataFailure(String message);

    void onLoadCommentData(List<Datum2> datas);

    void onLoadReplyCommentData(List<Datum3> datas);

    void onLoadPeopleLikedData(List<Datum4> datas);

    @POST("api/auth/login")
    Call<Example> getCurrentUserData(@Body DataEntered dataEntered);

    @GET("api/diary/get_by_season/{seasonId}")
    Call<Example2> getCurrentData(@Path("seasonId") String seasonId, @Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);

    @GET("api/comment/getList/5/{refer_id}")
    Call<Example3> getCurrentCommentData(@Path("refer_id") String refer_id, @Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);

    @GET("api/comment/getListByParent/{parent_id}")
    Call<Example4> getReplyCommentData(@Path("parent_id") String parent_id, @Query("token") String token);

    @POST("api/like/liked")
    Call<DataLiked> postDataLiked(@Header("Authorization") String token, @Body DataPostLiked dataPostLiked);

    @POST("api/like/unLiked")
    Call<DataLiked> postDataUnLiked(@Header("Authorization") String token, @Body DataPostLiked dataPostLiked);

    @GET("api/like/listLiked/5/{referId}")
    Call<DataPeopleLiked> getDataPeopleLiked(@Path("referId") String referid, @Query("token") String token);
}
