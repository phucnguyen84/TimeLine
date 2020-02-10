package com.volio.model;

import com.volio.model.entity.Example;
import com.volio.model.entity2.Example2;
import com.volio.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PushPresenter {
    public static LoadDataListener listener;
    public static String BareUrl="https://api.nextfarm.vn/";
    public static boolean check=false;
    public static String pageLimit="10";
    public static String page="1";
    public static String token;

    public PushPresenter(LoadDataListener listener) {
        this.listener = listener;
    }

    public void getCurrentData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BareUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoadDataListener service=retrofit.create(LoadDataListener.class);
        Call<Example> call=service.getCurrentUserData(MainActivity.dataEntered);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==200){
                    Example data=response.body();
                    assert data!=null;
                    token=data.getData().getToken();
                }
                else{
                    Example data=response.body();
                    listener.onLoadDataFailure(data.getMeta().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                String message=t.getMessage();
                listener.onLoadDataFailure(message);
            }
        });
        Call<Example2> call2=service.getCurrentData(pageLimit,page,token);
        call2.enqueue(new Callback<Example2>() {
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                if(response.code()==200){
                    Example2 data=response.body();
                    assert data!=null;
                    listener.onLoadDataSuccess(data.getData());
                }
            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {
                String message=t.getMessage();
                listener.onLoadDataFailure(message);
            }
        });
    }
}
