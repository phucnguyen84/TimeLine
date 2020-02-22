package com.volio.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volio.model.entity3.Datum2;
import com.volio.model.entity3.Sub;
import com.volio.model.entity4.Datum3;
import com.volio.presenter.MainPresenter;
import com.volio.view.R;
import com.volio.view.ReplyComment;
import com.volio.view.ReplyCommentView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> implements ReplyCommentView {
    private MainPresenter mainPresenter;
    private Context context;
    private List<Datum2> dataCmt;
    String parent_id;
    List<Datum3> dataReply = new ArrayList<>();

    public CommentAdapter(Context context, List<Datum2> dataCmt) {
        this.context = context;
        this.dataCmt = dataCmt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Datum2 datum2 = dataCmt.get(position);
        Glide.with(context)
                .load(datum2.getPicture())
                .into(holder.imgUserCmt);
        holder.txtUserCmt.setText(datum2.getFirstname() + " " + datum2.getLastname());
        holder.txtContentCmt.setText(datum2.getContent());
        Calendar calendar = Calendar.getInstance();
        String givenDateString = datum2.getCreateTime();
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date mDate = input.parse(givenDateString);
            String y = output.format(mDate);
            long timeInMilliseconds = mDate.getTime();
            long timeInMilliseconds2 = calendar.getTimeInMillis();
            if (timeInMilliseconds2 - timeInMilliseconds <= 60000) {
                holder.txtTimeCmt.setText("Just now");
            } else if (timeInMilliseconds2 - timeInMilliseconds < 3600000) {
                holder.txtTimeCmt.setText((int) (timeInMilliseconds2 - timeInMilliseconds) / 60000 + " Minutes ago");
            } else if (timeInMilliseconds2 - timeInMilliseconds < 86400000) {
                holder.txtTimeCmt.setText((int) (timeInMilliseconds2 - timeInMilliseconds) / 3600000 + " Hours ago");
            } else {
                holder.txtTimeCmt.setText(y);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (datum2.getLiked() == true) {
            holder.txtLikeCmt.setTextColor(Color.GREEN);
        } else {
            holder.txtLikeCmt.setTextColor(Color.parseColor("#A3A2A3"));
        }
        holder.txtLikeCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datum2.getLiked() == true) {
                    datum2.setLiked(false);
                    holder.txtLikeCmt.setTextColor(Color.parseColor("#A3A2A3"));
                } else {
                    datum2.setLiked(true);
                    holder.txtLikeCmt.setTextColor(Color.GREEN);
                }
            }
        });
        if (datum2.getTotalSub() > 3) {
            holder.txtSeemoreCmt.setVisibility(View.VISIBLE);
            holder.txtSeemoreCmt.setText("Xem thêm " + (datum2.getTotalSub() - 3) + " trả lời");
        } else {
            holder.txtSeemoreCmt.setVisibility(View.GONE);
        }
        mainPresenter = new MainPresenter(this);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent_id = datum2.getId() + "";
                mainPresenter.loadReplyCmtData(parent_id);
            }
        };
        holder.txtSeemoreCmt.setOnClickListener(onClickListener);
        holder.txtReply.setOnClickListener(onClickListener);
        if (datum2.getTotalSub() < 1) {
            holder.llReplyCmt1.setVisibility(View.GONE);
            holder.llReplyCmt2.setVisibility(View.GONE);
            holder.llReplyCmt3.setVisibility(View.GONE);
        } else if (datum2.getTotalSub() < 2) {
            holder.llReplyCmt1.setVisibility(View.VISIBLE);
            holder.llReplyCmt2.setVisibility(View.GONE);
            holder.llReplyCmt3.setVisibility(View.GONE);
            Glide.with(context)
                    .load(datum2.getSubs().get(0).getPicture())
                    .into(holder.imgUserCmtReply1);
            holder.txtUserCmtReply1.setText(datum2.getSubs().get(0).getFirstname() + " " + datum2.getSubs().get(0).getLastname());
            holder.txtContentCmtReply1.setText(datum2.getSubs().get(0).getContent());
        } else if (datum2.getTotalSub() < 3) {
            holder.llReplyCmt1.setVisibility(View.VISIBLE);
            holder.llReplyCmt2.setVisibility(View.VISIBLE);
            holder.llReplyCmt3.setVisibility(View.GONE);
            Glide.with(context)
                    .load(datum2.getSubs().get(0).getPicture())
                    .into(holder.imgUserCmtReply1);
            holder.txtUserCmtReply1.setText(datum2.getSubs().get(0).getFirstname() + " " + datum2.getSubs().get(0).getLastname());
            holder.txtContentCmtReply1.setText(datum2.getSubs().get(0).getContent());
            Glide.with(context)
                    .load(datum2.getSubs().get(1).getPicture())
                    .into(holder.imgUserCmtReply2);
            holder.txtUserCmtReply2.setText(datum2.getSubs().get(1).getFirstname() + " " + datum2.getSubs().get(1).getLastname());
            holder.txtContentCmtReply2.setText(datum2.getSubs().get(1).getContent());
        } else {
            holder.llReplyCmt1.setVisibility(View.VISIBLE);
            holder.llReplyCmt2.setVisibility(View.VISIBLE);
            holder.llReplyCmt3.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(datum2.getSubs().get(0).getPicture())
                    .into(holder.imgUserCmtReply1);
            holder.txtUserCmtReply1.setText(datum2.getSubs().get(0).getFirstname() + " " + datum2.getSubs().get(0).getLastname());
            holder.txtContentCmtReply1.setText(datum2.getSubs().get(0).getContent());
            Glide.with(context)
                    .load(datum2.getSubs().get(1).getPicture())
                    .into(holder.imgUserCmtReply2);
            holder.txtUserCmtReply2.setText(datum2.getSubs().get(1).getFirstname() + " " + datum2.getSubs().get(1).getLastname());
            holder.txtContentCmtReply2.setText(datum2.getSubs().get(1).getContent());
            Glide.with(context)
                    .load(datum2.getSubs().get(2).getPicture())
                    .into(holder.imgUserCmtReply3);
            holder.txtUserCmtReply3.setText(datum2.getSubs().get(2).getFirstname() + " " + datum2.getSubs().get(2).getLastname());
            holder.txtContentCmtReply3.setText(datum2.getSubs().get(2).getContent());
        }

    }

    @Override
    public int getItemCount() {
        return dataCmt.size();
    }

    @Override
    public void displayReplyCmtSuccess(List<Datum3> datas) {
        dataReply = datas;
        Intent intent = new Intent(context, ReplyComment.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("reply", (Serializable) datas);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUserCmt, imgUserCmtReply1, imgUserCmtReply2, imgUserCmtReply3;
        private TextView txtUserCmt, txtContentCmt, txtTimeCmt, txtLikeCmt, txtReply, txtSeemoreCmt, txtUserCmtReply1, txtContentCmtReply1,
                txtUserCmtReply2, txtContentCmtReply2, txtUserCmtReply3, txtContentCmtReply3;
        private LinearLayout llReplyCmt1, llReplyCmt2, llReplyCmt3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserCmt = itemView.findViewById(R.id.imgUserCmt);
            imgUserCmtReply1 = itemView.findViewById(R.id.imgUserCmtReply1);
            imgUserCmtReply2 = itemView.findViewById(R.id.imgUserCmtReply2);
            imgUserCmtReply3 = itemView.findViewById(R.id.imgUserCmtReply3);
            txtUserCmt = itemView.findViewById(R.id.txtUserCmt);
            txtContentCmt = itemView.findViewById(R.id.txtContentCmt);
            txtTimeCmt = itemView.findViewById(R.id.txtTimeCmt);
            txtLikeCmt = itemView.findViewById(R.id.txtLikeCmt);
            txtReply = itemView.findViewById(R.id.txtReply);
            txtSeemoreCmt = itemView.findViewById(R.id.txtSeemoreCmt);
            txtUserCmtReply1 = itemView.findViewById(R.id.txtUserCmtReply1);
            txtContentCmtReply1 = itemView.findViewById(R.id.txtContentCmtReply1);
            txtUserCmtReply2 = itemView.findViewById(R.id.txtUserCmtReply2);
            txtContentCmtReply2 = itemView.findViewById(R.id.txtContentCmtReply2);
            txtUserCmtReply3 = itemView.findViewById(R.id.txtUserCmtReply3);
            txtContentCmtReply3 = itemView.findViewById(R.id.txtContentCmtReply3);
            llReplyCmt1 = itemView.findViewById(R.id.llReplyCmt1);
            llReplyCmt2 = itemView.findViewById(R.id.llReplyCmt2);
            llReplyCmt3 = itemView.findViewById(R.id.llReplyCmt3);
        }
    }
}
