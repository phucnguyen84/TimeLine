package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.volio.model.CommentAdapter;
import com.volio.model.PostAdapter;
import com.volio.model.entity3.Datum2;
import com.volio.presenter.MainPresenter;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity{
    ImageView like,exitCmt;
    TextView txtCountLike;
    CommentAdapter commentAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        addControls();
        commentAdapter=new CommentAdapter(this, PostAdapter.DataCmt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);
    }

    private void addControls() {
        like=findViewById(R.id.like);
        txtCountLike=findViewById(R.id.txtCountLike);
        exitCmt=findViewById(R.id.exitCmt);
        recyclerView=findViewById(R.id.recyclerView3);
    }
}
