package com.volio.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volio.model.entityPeopleLiked.Datum4;
import com.volio.view.R;

import java.util.List;

public class PeopleLikedAdapter extends RecyclerView.Adapter<PeopleLikedAdapter.ViewHolder> {
    private Context context;
    private List<Datum4> datas;

    public PeopleLikedAdapter(Context context, List<Datum4> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_people_liked, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum4 data = datas.get(position);
        Glide.with(context)
                .load(data.getAvatar())
                .into(holder.imgUserLiked);
        holder.txtUserLiked.setText(data.getFirstname() + " " + data.getLastname());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUserLiked;
        TextView txtUserLiked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserLiked = itemView.findViewById(R.id.imgUserLiked);
            txtUserLiked = itemView.findViewById(R.id.txtUserLiked);
        }
    }
}
