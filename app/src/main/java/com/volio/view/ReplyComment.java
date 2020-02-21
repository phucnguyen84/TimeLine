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
import com.volio.model.entity4.Datum3;

import java.util.ArrayList;
import java.util.List;

public class ReplyComment extends AppCompatActivity {
    RecyclerView recyclerView;
    ReplyCommentAdapter replyCommentAdapter;
    Context context;
    ImageView backCmt;
    List<Datum3> dataReply = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_comment);
        addControls();
        backCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            dataReply = (List<Datum3>) bundle.getSerializable("reply");
        } else {
            return;
        }
//        List<Datum3> dataReply= (List<Datum3>) intent.getSerializableExtra("reply");

        if (dataReply.size() != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            replyCommentAdapter = new ReplyCommentAdapter(this, dataReply);
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
