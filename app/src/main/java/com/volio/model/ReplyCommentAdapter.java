package com.volio.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volio.model.entity3.Sub;
import com.volio.model.entity4.Datum3;
import com.volio.view.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReplyCommentAdapter extends RecyclerView.Adapter<ReplyCommentAdapter.ViewHolder> {
    private Context context;
    private List<Datum3> datas;

    public ReplyCommentAdapter(Context context, List<Datum3> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_reply_cmt, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Datum3 datum3 = datas.get(position);
        Glide.with(context)
                .load(datum3.getPicture())
                .into(holder.imgUserCmtReply);
        holder.txtUserCmtReply.setText(datum3.getFirstname() + " " + datum3.getLastname());
        holder.txtContentCmtReply.setText(datum3.getContent());
        Calendar calendar = Calendar.getInstance();
        String givenDateString = datum3.getCreateTime();
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date mDate = input.parse(givenDateString);
            String y = output.format(mDate);
            long timeInMilliseconds = mDate.getTime();
            long timeInMilliseconds2 = calendar.getTimeInMillis();
            if (timeInMilliseconds2 - timeInMilliseconds <= 60000) {
                holder.txtTimeCmtReply.setText("Just now");
            } else if (timeInMilliseconds2 - timeInMilliseconds < 3600000) {
                holder.txtTimeCmtReply.setText((int) (timeInMilliseconds2 - timeInMilliseconds) / 60000 + " Minutes ago");
            } else if (timeInMilliseconds2 - timeInMilliseconds < 86400000) {
                holder.txtTimeCmtReply.setText((int) (timeInMilliseconds2 - timeInMilliseconds) / 3600000 + " Hours ago");
            } else {
                holder.txtTimeCmtReply.setText(y);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (datum3.getLiked() == true) {
            holder.txtLikeCmtReply.setTextColor(Color.GREEN);
        } else {
            holder.txtLikeCmtReply.setTextColor(Color.parseColor("#A3A2A3"));
        }
        holder.txtLikeCmtReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datum3.getLiked() == true) {
                    datum3.setLiked(false);
                    holder.txtLikeCmtReply.setTextColor(Color.parseColor("#A3A2A3"));
                } else {
                    datum3.setLiked(true);
                    holder.txtLikeCmtReply.setTextColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUserCmtReply;
        private TextView txtUserCmtReply, txtContentCmtReply, txtTimeCmtReply, txtLikeCmtReply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserCmtReply = itemView.findViewById(R.id.imgUserCmtReply);
            txtUserCmtReply = itemView.findViewById(R.id.txtUserCmtReply);
            txtContentCmtReply = itemView.findViewById(R.id.txtContentCmtReply);
            txtTimeCmtReply = itemView.findViewById(R.id.txtTimeCmtReply);
            txtLikeCmtReply = itemView.findViewById(R.id.txtLikeCmtReply);
        }
    }
}
