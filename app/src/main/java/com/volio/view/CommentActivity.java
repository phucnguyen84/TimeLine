package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.volio.model.CommentAdapter;
import com.volio.model.PostAdapter;

public class CommentActivity extends AppCompatActivity {
    ImageView like, exitCmt;
    TextView txtCountLike;
    CommentAdapter commentAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        addControls();
        if (PostAdapter.like == true) {
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
                startActivity(new Intent(CommentActivity.this, LoginSuccess.class));
            }
        });
        txtCountLike.setText(PostAdapter.countLike + " Lượt thích");
        if (PostAdapter.DataCmt.size() != 0) {
//            recyclerView.setVisibility(View.VISIBLE);
            commentAdapter = new CommentAdapter(this, PostAdapter.DataCmt);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(commentAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
        }

    }

    private void addControls() {
        like = findViewById(R.id.like);
        txtCountLike = findViewById(R.id.txtCountLike);
        exitCmt = findViewById(R.id.exitCmt);
        recyclerView = findViewById(R.id.recyclerView3);
    }
}
