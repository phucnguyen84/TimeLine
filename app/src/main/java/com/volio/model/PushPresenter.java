package com.volio.model;

import com.volio.model.entity.Example;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Example3;
import com.volio.model.entity4.Example4;
import com.volio.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PushPresenter {
    public static LoadDataListener listener;
    public static String BareUrl = "https://api.nextfarm.vn/";
    public static String pageLimit = "11";
    public static String page = "1";
    public static String token;

    public PushPresenter(LoadDataListener listener) {
        this.listener = listener;
    }

    public void getCurrentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<Example> call1 = service.getCurrentUserData(MainActivity.dataEntered);
        call1.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 200) {
                    Example data = response.body();
                    assert data != null;
                    token = data.getData().getToken();
                    Call<Example2> call2 = service.getCurrentData("5", pageLimit, page, token);
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
                            String message = t.getMessage();
                            listener.onLoadDataFailure(message);
                        }
                    });
                } else {
                    Example data = response.body();
                    listener.onLoadDataFailure(data.getMeta().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                String message = t.getMessage();
                listener.onLoadDataFailure(message);
            }
        });
    }

    public void getCommentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<Example3> call = service.getCurrentCommentData(PostAdapter.refer_id, PostAdapter.pageLimit, page, token);
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
                String message = t.getMessage();
                listener.onLoadDataFailure(message);
            }
        });
    }

    public void getReplyCommentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoadDataListener service = retrofit.create(LoadDataListener.class);
        Call<Example4> call = service.getReplyCommentData(CommentAdapter.parent_id, token);
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
                String message = t.getMessage();
                listener.onLoadDataFailure(message);
            }
        });
    }
}
