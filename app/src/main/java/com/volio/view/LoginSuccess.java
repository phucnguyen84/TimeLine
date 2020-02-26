package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.volio.model.ILoadMore;
import com.volio.model.PostAdapter;
import com.volio.model.entity2.Datum;

import java.util.ArrayList;
import java.util.List;

public class LoginSuccess extends AppCompatActivity {
    ArrayList<Datum> mDatums = new ArrayList<>();
    RecyclerView recyclerView1;
    PostAdapter postAdapter;
    ImageView imageView;

    List<Datum> data = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        addControls();
        final Intent intent = getIntent();
        Bundle bundle;
        bundle = intent.getExtras();
        if (bundle != null) {
            data = (List<Datum>) bundle.getSerializable("data");
        } else {
            return;
        }
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(recyclerView1, this, mDatums);
        recyclerView1.setAdapter(postAdapter);
        addEvents();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDatums.clear();
                mDatums.addAll(data);
//                finish();
//                startActivity(intent);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    private void addEvents() {
        for (int i = 0; i < 5; i++) {
            mDatums.add(data.get(i));
        }
        postAdapter.setLoadMore(new ILoadMore() {
            @Override
            public void OnLoadMore() {
                if (mDatums.size() < data.size()) {
                    mDatums.add(null);
                    postAdapter.notifyItemInserted(mDatums.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDatums.remove(mDatums.size() - 1);
                            postAdapter.notifyItemRemoved(mDatums.size());

                            int index = mDatums.size();
                            int end = index + 5;
                            for (int i = index; i < end; i++) {
                                if (i < data.size()) {
                                    mDatums.add(data.get(i));
                                } else
                                    break;
                            }
                            postAdapter.notifyDataSetChanged();
                            postAdapter.setLoaded();
                        }
                    }, 3000);
                }
            }
        });
    }

    private void addControls() {
        recyclerView1 = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        imageView = findViewById(R.id.imgPost33);
    }
}
