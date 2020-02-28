package com.volio.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volio.model.entity2.Datum;
import com.volio.model.entity3.Datum2;
import com.volio.model.entityLiked.DataPostLiked;
import com.volio.model.entityPeopleLiked.Datum4;
import com.volio.presenter.MainPresenter;
import com.volio.view.CommentActivity;
import com.volio.view.CommentView;
import com.volio.view.ImageAdd;
import com.volio.view.PeopleLiked;
import com.volio.view.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView txtUser, txtTime, txtPost, txtLike, txtCmt, txtPostAdd;
    public LinearLayout btnLike, btnCmt;
    public ImageView btnLiked, imgUser, imgPost11, imgPost31, imgPost32, imgPost33, imgPost21, imgPost22;
    public RecyclerView recyclerView;
    public LinearLayout ll1, ll2, ll3;

    public ItemViewHolder(View itemView) {
        super(itemView);
        txtUser = itemView.findViewById(R.id.txtUser);
        txtTime = itemView.findViewById(R.id.txtTime);
        txtPost = itemView.findViewById(R.id.txtPost);
        txtLike = itemView.findViewById(R.id.txtLike);
        txtCmt = itemView.findViewById(R.id.txtCmt);
        btnLike = itemView.findViewById(R.id.btnLike);
        btnCmt = itemView.findViewById(R.id.btnCmt);
        btnLiked = itemView.findViewById(R.id.btnLiked);
        imgUser = itemView.findViewById(R.id.imgUser);
        imgPost11 = itemView.findViewById(R.id.imgPost11);
        imgPost31 = itemView.findViewById(R.id.imgPost31);
        imgPost32 = itemView.findViewById(R.id.imgPost32);
        imgPost33 = itemView.findViewById(R.id.imgPost33);
        recyclerView = itemView.findViewById(R.id.recyclerView2);
        txtPostAdd = itemView.findViewById(R.id.txtPostAdd);
        imgPost21 = itemView.findViewById(R.id.imgPost21);
        imgPost22 = itemView.findViewById(R.id.imgPost22);
        ll1 = itemView.findViewById(R.id.ll1);
        ll2 = itemView.findViewById(R.id.ll2);
        ll3 = itemView.findViewById(R.id.ll3);
    }
}

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CommentView {
    private MainPresenter mainPresenter;
    public String countLike;

    PestAdapter pestAdapter;
    String[] img = new String[100];
    String refer_id;
    String pageLimit;
    boolean like;


    String img_Post;

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading = false;
    Context context;
    ArrayList<Datum> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public PostAdapter(RecyclerView recyclerView, Context context, ArrayList<Datum> items) {
        this.context = context;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMore != null)
                        loadMore.OnLoadMore();
                    isLoading = true;
                }

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemViewHolder) {
            final Datum item = items.get(position);
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            Glide.with(context)
                    .load(item.getUser().getPicture())
                    .into(((ItemViewHolder) holder).imgUser);
            viewHolder.txtUser.setText(item.getUser().getFirstname() + " " + item.getUser().getLastname());
            Calendar calendar = Calendar.getInstance();
            String givenDateString = item.getCreatedDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date mDate = sdf.parse(givenDateString);
                String y = output.format(mDate);
                long timeInMilliseconds = mDate.getTime();
                long timeInMilliseconds2 = calendar.getTimeInMillis();
                if (timeInMilliseconds2 - timeInMilliseconds <= 60000) {
                    viewHolder.txtTime.setText("Just now");
                } else if (timeInMilliseconds2 - timeInMilliseconds < 3600000) {
                    viewHolder.txtTime.setText((int) (timeInMilliseconds2 - timeInMilliseconds) / 60000 + " Minutes ago");
                } else if (timeInMilliseconds2 - timeInMilliseconds < 86400000) {
                    viewHolder.txtTime.setText((int) (timeInMilliseconds2 - timeInMilliseconds) / 3600000 + " Hours ago");
                } else {
                    viewHolder.txtTime.setText(y);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            viewHolder.txtPost.setText(item.getContent());
            String json_string = item.getImage();
            String json_string3 = "{" + "\"image\":" + json_string + "}" + "}";

            try {
                JSONObject root = new JSONObject(json_string3);
                JSONArray array = root.getJSONArray("image");
                setValue(json_string, array, viewHolder.ll1, viewHolder.ll2, viewHolder.ll3);

                if (array.length() == 1) {
                    JSONObject object = array.getJSONObject(0);
                    Glide.with(context)
                            .load(object.getString("url"))
                            .into(viewHolder.imgPost11);
                } else if (array.length() == 2) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        if (i == 0) {
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost21);
                        }
                        if (i == 1) {
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost22);
                        }
                    }
                } else if (array.length() > 2) {
                    for (int j = 0; j < array.length(); j++) {
                        JSONObject object = array.getJSONObject(j);
                        if (j == 0) {
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost31);
                            if (array.length() > 3) {
                                img_Post = object.getString("url");
                                img[position] = img_Post;
                            }
                        } else if (j == 1) {
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost32);
                        } else if (j == 2) {
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost33);
                        }
                        if ((j == array.length() - 1) && array.length() > 3) {
                            int x = j - 2;
                            viewHolder.txtPostAdd.setText("+" + x);
                            viewHolder.txtPostAdd.setTextSize(40);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (json_string == null) {
                hideViews(viewHolder.ll1, viewHolder.ll2, viewHolder.ll3);
            }
            viewHolder.txtPostAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_Post = img[position];
                    Intent intent = new Intent(context, ImageAdd.class);
                    intent.putExtra("imgpost", img_Post);
                    v.getContext().startActivity(intent);
                    viewHolder.txtPostAdd.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.txtPostAdd.setEnabled(true);
                        }
                    },2000);
                }
            });
            viewHolder.txtLike.setText(item.getCountLiked() + " Likes");
            viewHolder.txtLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainPresenter.loadPeopleLikedData(item.getId() + "");
                    viewHolder.txtLike.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.txtLike.setEnabled(true);
                        }
                    },2000);
                }
            });
            viewHolder.txtCmt.setText(item.getCountComments() + " Comments");
            if (item.getLiked() == true) {
                Glide.with(context)
                        .load(R.drawable.liked)
                        .into(((ItemViewHolder) holder).btnLiked);
            } else {
                Glide.with(context)
                        .load(R.drawable.like)
                        .into(((ItemViewHolder) holder).btnLiked);
            }
            mainPresenter = new MainPresenter(this);
            viewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataPostLiked dataPostLiked = new DataPostLiked(item.getId() + "", "5");
                    if (item.getLiked() == true) {
                        item.setLiked(false);
                        mainPresenter.loadUnLikedData(dataPostLiked);
                        Glide.with(context)
                                .load(R.drawable.like)
                                .into(viewHolder.btnLiked);
                        item.setCountLiked(item.getCountLiked() - 1);
                        viewHolder.txtLike.setText(item.getCountLiked() + " Likes");
                    } else {
                        item.setLiked(true);
                        mainPresenter.loadLikedData(dataPostLiked);
                        Glide.with(context)
                                .load(R.drawable.liked)
                                .into(viewHolder.btnLiked);
                        item.setCountLiked(item.getCountLiked() + 1);
                        viewHolder.txtLike.setText(item.getCountLiked() + " Likes");
                    }
                }
            });
            if (item.getPests().size() != 0 ||
                    item.getFertilizers().size() != 0 ||
                    item.getProducts().size() != 0 ||
                    item.getTransporters().size() != 0 ||
                    item.getPackages().size() != 0) {
                viewHolder.recyclerView.setVisibility(View.VISIBLE);
                List<String> list = new ArrayList<>();
                if (item.getPests().size() > 0) {
                    for (int i = 0; i < item.getPests().size(); i++) {
                        list.add(item.getPests().get(i).getName());
                    }
                }
                if (item.getFertilizers().size() > 0) {
                    for (int i = 0; i < item.getFertilizers().size(); i++) {
                        list.add(item.getFertilizers().get(i).getName());
                    }
                }
                if (item.getProducts().size() > 0) {
                    for (int i = 0; i < item.getProducts().size(); i++) {
                        list.add(item.getProducts().get(i).getName());
                    }
                }
                if (item.getTransporters().size() > 0) {
                    for (int i = 0; i < item.getTransporters().size(); i++) {
                        list.add(item.getTransporters().get(i).getName());
                    }
                }
                if (item.getPackages().size() > 0) {
                    for (int i = 0; i < item.getPackages().size(); i++) {
                        list.add(item.getPackages().get(i).getName());
                    }
                }
                pestAdapter = new PestAdapter(context, list);
                viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                viewHolder.recyclerView.setAdapter(pestAdapter);
            } else {
                viewHolder.recyclerView.setVisibility(View.GONE);
            }
            viewHolder.btnCmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pageLimit = item.getCountComments() + "";
                    refer_id = item.getId() + "";
                    mainPresenter.loadCommentData(refer_id, pageLimit);
                    countLike = item.getCountLiked() + "";
                    like = item.getLiked();
                    viewHolder.btnCmt.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.btnCmt.setEnabled(true);
                        }
                    },2000);
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    void setValue(String value, JSONArray array, LinearLayout row1, LinearLayout row2, LinearLayout row3) {
        if (value != null) {
            if (array.length() == 1) {
                showViews1(row1, row2, row3);
            } else if (array.length() == 2) {
                showViews2(row1, row2, row3);
            } else if (array.length() >= 3) {
                showViews3(row1, row2, row3);
            }
        }
    }

    private void showViews1(LinearLayout row1, LinearLayout row2, LinearLayout row3) {
        row1.setVisibility(View.VISIBLE);
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
    }

    private void showViews2(LinearLayout row1, LinearLayout row2, LinearLayout row3) {
        row1.setVisibility(View.GONE);
        row2.setVisibility(View.VISIBLE);
        row3.setVisibility(View.GONE);
    }

    private void showViews3(LinearLayout row1, LinearLayout row2, LinearLayout row3) {
        row1.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.VISIBLE);
    }

    private void hideViews(LinearLayout row1, LinearLayout row2, LinearLayout row3) {
        row1.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public void displayCmtSuccess(List<Datum2> data) {
        Intent intent = new Intent(context, CommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("comment", (Serializable) data);
        bundle.putString("countlike", countLike);
        bundle.putBoolean("like", like);
        bundle.putString("referid", refer_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void displayPeopleLiked(List<Datum4> data) {
        Intent intent = new Intent(context, PeopleLiked.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("liked", (Serializable) data);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}