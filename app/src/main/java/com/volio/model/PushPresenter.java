package com.volio.model;

import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Example3;
import com.volio.model.entity4.Example4;
import com.volio.model.entityLiked.Data;
import com.volio.model.entityLiked.DataLiked;
import com.volio.model.entityLiked.DataPostLiked;
import com.volio.model.entityPeopleLiked.DataPeopleLiked;
import com.volio.model.entityPeopleLiked.Datum4;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PushPresenter {
    LoadDataListener listener;
    String BareUrl = "https://api.nextfarm.vn/";
    String pageLimit = "50";
    String page = "1";
    public static String token;


    public PushPresenter(LoadDataListener listener) {
        this.listener = listener;
    }

    public void getCurrentData(DataEntered dataEntered) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<Example> call1 = service.getCurrentUserData(dataEntered);
        call1.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 200) {
                    Example data = response.body();
                    assert data != null;
                    if (data.getData() != null) {
                        token = data.getData().getToken();
                        Call<Example2> call2 = service.getCurrentData("127", pageLimit, page, token);
                        call2.enqueue(new Callback<Example2>() {
                            @Override
                            public void onResponse(Call<Example2> call, Response<Example2> response) {
                                if (response.code() == 200) {
                                    Example2 data = response.body();
                                    assert data != null;
                                    listener.onLoadDataSuccess(data.getData());
                                }
                            }

                            @Override
                            public void onFailure(Call<Example2> call, Throwable t) {

                            }
                        });
                    } else {
                        listener.onLoadDataFailure(data.getMeta().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    public void postDataLiked(DataPostLiked dataPostLiked) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<DataLiked> call = service.postDataLiked("Bearer" + token, dataPostLiked);
        call.enqueue(new Callback<DataLiked>() {
            @Override
            public void onResponse(Call<DataLiked> call, Response<DataLiked> response) {
                if (response.code() == 200) {
                    DataLiked data = response.body();
                    assert data != null;
                }
            }

            @Override
            public void onFailure(Call<DataLiked> call, Throwable t) {

            }
        });
    }

    public void postDataUnLiked(DataPostLiked dataPostLiked) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<DataLiked> call = service.postDataUnLiked("Bearer" + token, dataPostLiked);
        call.enqueue(new Callback<DataLiked>() {
            @Override
            public void onResponse(Call<DataLiked> call, Response<DataLiked> response) {
                if (response.code() == 200) {
                    DataLiked data = response.body();
                    assert data != null;
                }
            }

            @Override
            public void onFailure(Call<DataLiked> call, Throwable t) {

            }
        });
    }

    public void getCommentData(String refer_id, String pageLimit2) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<Example3> call = service.getCurrentCommentData(refer_id, pageLimit2, page, token);
        call.enqueue(new Callback<Example3>() {
            @Override
            public void onResponse(Call<Example3> call, Response<Example3> response) {
                if (response.code() == 200) {
                    Example3 data = response.body();
                    assert data != null;
                    listener.onLoadCommentData(data.getData());
                }
            }

            @Override
            public void onFailure(Call<Example3> call, Throwable t) {

            }
        });
    }

    public void getReplyCommentData(String parent_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<Example4> call = service.getReplyCommentData(parent_id, token);
        call.enqueue(new Callback<Example4>() {
            @Override
            public void onResponse(Call<Example4> call, Response<Example4> response) {
                if (response.code() == 200) {
                    Example4 data = response.body();
                    assert data != null;
                    listener.onLoadReplyCommentData(data.getData());
                }
            }

            @Override
            public void onFailure(Call<Example4> call, Throwable t) {

            }
        });
    }


    public void getDataPeopleLiked(String referid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<DataPeopleLiked> call = service.getDataPeopleLiked(referid, token);
        call.enqueue(new Callback<DataPeopleLiked>() {
            @Override
            public void onResponse(Call<DataPeopleLiked> call, Response<DataPeopleLiked> response) {
                if (response.code() == 200) {
                    DataPeopleLiked data = response.body();
                    assert data != null;
                    listener.onLoadPeopleLikedData(data.getData());
                }
            }

            @Override
            public void onFailure(Call<DataPeopleLiked> call, Throwable t) {

            }
        });
    }
}
