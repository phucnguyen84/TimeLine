package com.volio.presenter;

import com.volio.model.LoadDataListener;
import com.volio.model.PushPresenter;
import com.volio.model.entity.DataEntered;
import com.volio.model.entity.Example;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Example2;
import com.volio.model.entity3.Datum2;
import com.volio.model.entity3.Example3;
import com.volio.model.entity4.Datum3;
import com.volio.model.entity4.Example4;
import com.volio.model.entityLiked.Data;
import com.volio.model.entityLiked.DataLiked;
import com.volio.model.entityLiked.DataPostLiked;
import com.volio.model.entityPeopleLiked.DataPeopleLiked;
import com.volio.model.entityPeopleLiked.Datum4;
import com.volio.view.CommentView;
import com.volio.view.LoginView;
import com.volio.view.ReplyCommentView;

import java.util.List;

import retrofit2.Call;

public class MainPresenter implements LoadDataListener {
    private PushPresenter pushPresenter;
    private LoginView loginView;
    private CommentView commentView;
    private ReplyCommentView replyCommentView;

    public MainPresenter(LoginView loginView) {
        this.loginView = loginView;
        pushPresenter = new PushPresenter(this);
    }

    public MainPresenter(CommentView commentView) {
        this.commentView = commentView;
        pushPresenter = new PushPresenter(this);
    }

    public MainPresenter(ReplyCommentView replyCommentView) {
        this.replyCommentView = replyCommentView;
        pushPresenter = new PushPresenter(this);
    }

    public void loadData(DataEntered dataEntered) {
        pushPresenter.getCurrentData(dataEntered);
    }

    public void loadCommentData(String refer_id, String pageLimit) {
        pushPresenter.getCommentData(refer_id, pageLimit);
    }

    public void loadReplyCmtData(String parent_id) {
        pushPresenter.getReplyCommentData(parent_id);
    }

    public void loadLikedData(DataPostLiked dataPostLiked) {
        pushPresenter.postDataLiked(dataPostLiked);
    }

    public void loadUnLikedData(DataPostLiked dataPostLiked) {
        pushPresenter.postDataUnLiked(dataPostLiked);
    }

    public void loadPeopleLikedData(String referid) {
        pushPresenter.getDataPeopleLiked(referid);
    }

    @Override
    public void onLoadDataSuccess(List<Datum> datas) {
        loginView.displayLoginSuccess(datas);
    }

    @Override
    public void onLoadCommentData(List<Datum2> datas) {
        commentView.displayCmtSuccess(datas);
    }

    @Override
    public void onLoadReplyCommentData(List<Datum3> datas) {
        replyCommentView.displayReplyCmtSuccess(datas);
    }

    @Override
    public void onLoadPeopleLikedData(List<Datum4> datas) {
        commentView.displayPeopleLiked(datas);
    }

    @Override
    public Call<Example> getCurrentUserData(DataEntered dataEntered) {
        return null;
    }

    @Override
    public Call<Example2> getCurrentData(String seasonId, String pageLimit, String page, String token) {
        return null;
    }

    @Override
    public Call<Example3> getCurrentCommentData(String refer_id, String pageLimit, String page, String token) {
        return null;
    }

    @Override
    public Call<Example4> getReplyCommentData(String parent_id, String token) {
        return null;
    }

    @Override
    public Call<DataLiked> postDataLiked(String token, DataPostLiked dataPostLiked) {
        return null;
    }

    @Override
    public Call<DataLiked> postDataUnLiked(String token, DataPostLiked dataPostLiked) {
        return null;
    }

    @Override
    public Call<DataPeopleLiked> getDataPeopleLiked(String referid, String token) {
        return null;
    }
}
