package com.volio.presenter;

import com.volio.model.LoadDataListener;
import com.volio.model.PushPresenter;
import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Datum2;
import com.volio.model.entity3.Example3;
import com.volio.view.CommentView;
import com.volio.view.LoginView;

import java.util.ArrayList;

import retrofit2.Call;

public class MainPresenter implements LoadDataListener {
    private PushPresenter pushPresenter;
    private LoginView loginView;
    private CommentView commentView;

    public MainPresenter(LoginView loginView) {
        this.loginView = loginView;
        pushPresenter = new PushPresenter(this);
    }

    public MainPresenter(CommentView commentView) {
        this.commentView = commentView;
        pushPresenter = new PushPresenter(this);
    }

    public void loadData() {
        pushPresenter.getCurrentData();
    }

    public void loadCommentData(){
        pushPresenter.getCommentData();
    }

    @Override
    public void onLoadDataSuccess(ArrayList<Datum> datas) {
        loginView.displayLoginSuccess(datas);
    }

    @Override
    public void onLoadCommentData(ArrayList<Datum2> data2s) {
        commentView.displayCmtSuccess(data2s);
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

    @Override
    public Call<Example3> getCurrentCommentData(String refer_id, String pageLimit, String page, String token) {
        return null;
    }
}
