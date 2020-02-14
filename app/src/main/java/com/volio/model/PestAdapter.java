package com.volio.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.volio.model.entity2.Fertilizer;
import com.volio.model.entity2.Package;
import com.volio.model.entity2.Pest;
import com.volio.model.entity2.Product;
import com.volio.model.entity2.Transporter;
import com.volio.view.R;

import java.util.ArrayList;
import java.util.List;

public class PestAdapter extends
        RecyclerView.Adapter<PestAdapter.ViewHolder> {
    private Context mContext;
    private List<String> list;

    public PestAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String x = list.get(position);
        holder.txtPest.setText(x);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtPest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPest = itemView.findViewById(R.id.txtPest);
        }
    }

}