package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.volio.model.CommentAdapter;
import com.volio.model.PostAdapter;
import com.volio.model.entity3.Datum2;
import com.volio.model.entityPeopleLiked.Datum4;
import com.volio.presenter.MainPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements CommentView {
    ImageView like, exitCmt;
    TextView txtCountLike;
    CommentAdapter commentAdapter;
    RecyclerView recyclerView;
    List<Datum2> dataCmt = new ArrayList<>();
    String countLike, refer_id;
    Boolean liked;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        addControls();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            dataCmt = (List<Datum2>) bundle.getSerializable("comment");
            countLike = bundle.getString("countlike");
            liked = bundle.getBoolean("like");
            refer_id = bundle.getString("referid");
        } else {
            return;
        }
        if (liked == true) {
            Glide.with(this)
                    .load(R.drawable.liked)
                    .into(like);
        } else {
            Glide.with(this)
                    .load(R.drawable.like)
                    .into(like);
        }
        exitCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtCountLike.setText(countLike + " Likes");
        mainPresenter = new MainPresenter(this);
        txtCountLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.loadPeopleLikedData(refer_id);
                txtCountLike.setEnabled(false);
            }
        });
        if (dataCmt.size() != 0) {
            commentAdapter = new CommentAdapter(this, dataCmt);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(commentAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtCountLike.setEnabled(true);
    }

    private void addControls() {
        like = findViewById(R.id.like);
        txtCountLike = findViewById(R.id.txtCountLike);
        exitCmt = findViewById(R.id.exitCmt);
        recyclerView = findViewById(R.id.recyclerView3);
    }

    @Override
    public void displayCmtSuccess(List<Datum2> data2s) {

    }

    @Override
    public void displayPeopleLiked(List<Datum4> data) {
        Intent intent = new Intent(CommentActivity.this, PeopleLiked.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("liked", (Serializable) data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
