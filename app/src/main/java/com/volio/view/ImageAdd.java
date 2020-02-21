package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.volio.model.PostAdapter;

public class ImageAdd extends AppCompatActivity {
    ImageView imgPostAdd, backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add);
        Intent intent = getIntent();
        String imgPost = intent.getStringExtra("imgpost");
        imgPostAdd = findViewById(R.id.imgPostAdd);
        backImg = findViewById(R.id.backImg);
        Glide.with(ImageAdd.this)
                .load(imgPost)
                .into(imgPostAdd);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
