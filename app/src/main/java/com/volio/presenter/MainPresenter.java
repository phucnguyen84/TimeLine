package com.volio.presenter;

import com.volio.model.LoadDataListener;
import com.volio.model.PushPresenter;
import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;
import com.volio.view.LoginView;

import java.util.ArrayList;

import retrofit2.Call;

public class MainPresenter implements LoadDataListener {
    private PushPresenter pushPresenter;
    private LoginView loginView;

    public MainPresenter(LoginView loginView) {
        this.loginView = loginView;
        pushPresenter = new PushPresenter(this);
    }

    public void loadData(){
        pushPresenter.getCurrentData();
    }

    @Override
    public void onLoadDataSuccess(ArrayList<Datum> datas) {
        loginView.displayLoginSuccess(datas);
    }

    @Override
    public void onLoadDataFailure(String message) {
        loginView.displayLoginFailure(message);
    }

    @Override
    public Call<Example> getCurrentUserData(DataEntered dataEntered) {
        return null;
    }

    @Override
    public Call<Example2> getCurrentData(String pageLimit, String page, String token) {
        return null;
    }
}
