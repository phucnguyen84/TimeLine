package com.volio.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.volio.model.DetailPhotosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class ImageAdd extends AppCompatActivity {
    private ImageView imgBack;
    private ViewPager viewPagerDetailPhoto;
    private DetailPhotosAdapter detailPhotosAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_add);
        initView();
        event();
    }


    private void initView() {
        viewPagerDetailPhoto = findViewById(R.id.vpDetailPhoto);
        imgBack = findViewById(R.id.backImg);
    }


    private void event() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        List<String> images = new ArrayList<>();
        String json_image = "{\"image\":" + intent.getStringExtra("json") + "}";
        try {

            JSONObject mImagesJsonObject = new JSONObject(json_image);
            JSONArray mImagesJsonArray = mImagesJsonObject.getJSONArray("image");
            for (int i = 0; i < mImagesJsonArray.length(); i++) {
                JSONObject image = mImagesJsonArray.getJSONObject(i);
                images.add(image.getString("url"));
            }
            detailPhotosAdapter = new DetailPhotosAdapter(ImageAdd.this, images);
            viewPagerDetailPhoto.setAdapter(detailPhotosAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
