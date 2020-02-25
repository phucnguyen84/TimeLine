package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.volio.model.PeopleLikedAdapter;
import com.volio.model.entityPeopleLiked.Datum4;

import java.util.ArrayList;
import java.util.List;

public class PeopleLiked extends AppCompatActivity {
    private List<Datum4> datas = new ArrayList<>();
    private ImageView imgBackLiked;
    private RecyclerView recyclerView;
    private PeopleLikedAdapter peopleLikedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_liked);
        addControls();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            datas = (List<Datum4>) bundle.getSerializable("liked");
        } else {
            return;
        }
        if (datas.size() != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            peopleLikedAdapter = new PeopleLikedAdapter(this, datas);
            recyclerView.setAdapter(peopleLikedAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
        imgBackLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        imgBackLiked = findViewById(R.id.backLiked);
        recyclerView = findViewById(R.id.recyclerView5);
    }
}
