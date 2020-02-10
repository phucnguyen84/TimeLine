package com.volio.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volio.model.entity2.Datum;
import com.volio.model.entity2.Pest;
import com.volio.view.ImageAdd;
import com.volio.view.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder
{

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView txtUser,txtTime,txtPost,txtLike,txtCmt,txtPostAdd,txt;
    public LinearLayout btnLike,btnCmt;
    public ImageView btnLiked,imgUser,imgPost1,imgPost2,imgPost3,imgPost21,imgPost22;
    public RecyclerView recyclerView;
    public List<Pest> mPests=new ArrayList<>();



    public ItemViewHolder(View itemView) {
        super(itemView);
        txtUser=itemView.findViewById(R.id.txtUser);
        txtTime=itemView.findViewById(R.id.txtTime);
        txtPost=itemView.findViewById(R.id.txtPost);
        txtLike=itemView.findViewById(R.id.txtLike);
        txtCmt=itemView.findViewById(R.id.txtCmt);
        btnLike=itemView.findViewById(R.id.btnLike);
        btnCmt=itemView.findViewById(R.id.btnCmt);
        btnLiked=itemView.findViewById(R.id.btnLiked);
        imgUser=itemView.findViewById(R.id.imgUser);
        imgPost1=itemView.findViewById(R.id.imgPost1);
        imgPost2=itemView.findViewById(R.id.imgPost2);
        imgPost3=itemView.findViewById(R.id.imgPost3);
        recyclerView=itemView.findViewById(R.id.recyclerView2);
        txtPostAdd=itemView.findViewById(R.id.txtPostAdd);
        imgPost21=itemView.findViewById(R.id.imgPost21);
        imgPost22=itemView.findViewById(R.id.imgPost22);
        txt=itemView.findViewById(R.id.txt);
    }
}

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    PestAdapter pestAdapter;

    public static String img_Post="";


    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    ILoadMore loadMore;
    boolean isLoading=false;
    Context context;
    ArrayList<Datum> items;
    int visibleThreshold=5;
    int lastVisibleItem,totalItemCount;

    public PostAdapter(RecyclerView recyclerView, Context context, ArrayList<Datum> items) {
        this.context = context;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold))
                {
                    if(loadMore != null)
                        loadMore.OnLoadMore();
                    isLoading = true;
                }

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM)
        {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item,parent,false);
            return new ItemViewHolder(view);
        }
        else if(viewType == VIEW_TYPE_LOADING)
        {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof ItemViewHolder)
        {
            final Datum item = items.get(position);
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.txtUser.setText(items.get(position).getUser().getFirstname()+" "+items.get(position).getUser().getLastname());
            viewHolder.txtTime.setText(items.get(position).getCreatedDate());
            viewHolder.txtPost.setText(items.get(position).getContent());
            String json_string=items.get(position).getImage();
//            String json_string2=json_string.replace("\\","");
            String json_string3="{"+"\"image\":"+json_string+"}"+"}";

            try {

                JSONObject root = new JSONObject(json_string3);
                JSONArray array= root.getJSONArray("image");
                if(array.length()==2){
                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject object= array.getJSONObject(i);
                            if(i==0){
                                Glide.with(context)
                                        .load(object.getString("url"))
                                        .into(viewHolder.imgPost21);
                                viewHolder.imgPost21.getLayoutParams().height=500;
                                viewHolder.txt.setText(object.getString("url"));

                            }
                            if(i==1){
                                Glide.with(context)
                                        .load(object.getString("url"))
                                        .into(viewHolder.imgPost22);
                                viewHolder.imgPost21.getLayoutParams().height=500;

                            }
                        }
                }else{
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object= array.getJSONObject(i);
                        if(i==0){
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost1);
                            viewHolder.imgPost1.getLayoutParams().height=1000;
                            if(array.length()>3){
                                img_Post=object.getString("url");
                            }
                        }else
                        if(i==1){
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost2);
                        }else
                        if(i==2){
                            Glide.with(context)
                                    .load(object.getString("url"))
                                    .into(viewHolder.imgPost3);
                        }
                        if((i==array.length()-1)&&array.length()>3){
                            int x=i-2;
                            viewHolder.txtPostAdd.setText("+"+x);
                            viewHolder.txtPostAdd.setTextSize(40);
                        }
                    }
                }
                viewHolder.imgPost3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context,ImageAdd.class);
                        v.getContext().startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

            viewHolder.txtLike.setText(items.get(position).getCountLiked()+" Likes");
            viewHolder.txtCmt.setText(items.get(position).getCountComments()+" Comment");
            Glide.with(context)
                    .load(item.getUser().getPicture())
                    .into(((ItemViewHolder) holder).imgUser);
            if(items.get(position).getLiked()==true){
                Glide.with(context)
                        .load(R.drawable.liked)
                        .into(((ItemViewHolder) holder).btnLiked);
            }else{
                Glide.with(context)
                        .load(R.drawable.like)
                        .into(((ItemViewHolder) holder).btnLiked);
            }
            viewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(items.get(position).getLiked()==true){
                        items.get(position).setLiked(false);
                        Glide.with(context)
                                .load(R.drawable.like)
                                .into(viewHolder.btnLiked);
                        items.get(position).setCountLiked(items.get(position).getCountLiked()-1);
                        viewHolder.txtLike.setText(items.get(position).getCountLiked()+" Likes");
                    }else{
                        items.get(position).setLiked(true);
                        Glide.with(context)
                                .load(R.drawable.liked)
                                .into(viewHolder.btnLiked);
                        items.get(position).setCountLiked(items.get(position).getCountLiked()+1);
                        viewHolder.txtLike.setText(items.get(position).getCountLiked()+" Likes");
                    }
                }
            });

            viewHolder.mPests.addAll(items.get(position).getPests());
            viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            pestAdapter = new PestAdapter(context,viewHolder.mPests);
            viewHolder.recyclerView.setAdapter(pestAdapter);
        }
        else if(holder instanceof LoadingViewHolder)
        {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}