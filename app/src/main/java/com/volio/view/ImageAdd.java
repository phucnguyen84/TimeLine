package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.volio.model.PostAdapter;

public class ImageAdd extends AppCompatActivity {
    ImageView imgPostAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add);
        imgPostAdd=findViewById(R.id.imgPostAdd);
        Glide.with(ImageAdd.this)
                .load(PostAdapter.img_Post)
                .into(imgPostAdd);
    }
}
