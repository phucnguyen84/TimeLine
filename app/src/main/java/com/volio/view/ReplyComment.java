package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.volio.model.CommentAdapter;
import com.volio.model.ReplyCommentAdapter;

public class ReplyComment extends AppCompatActivity {
    RecyclerView recyclerView;
    ReplyCommentAdapter replyCommentAdapter;
    Context context;
    ImageView backCmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_comment);
        addControls();
        backCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplyComment.this, CommentActivity.class));
            }
        });
        if (CommentAdapter.dataReply.size() != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            replyCommentAdapter = new ReplyCommentAdapter(this, CommentAdapter.dataReply);
            recyclerView.setAdapter(replyCommentAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void addControls() {
        backCmt = findViewById(R.id.backCmt);
        recyclerView = findViewById(R.id.recyclerView4);
    }
}
