package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.model.restaurant_reviews.RestaurantReviewsData;
import com.example.sofra.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReguestComment_RateAdapter extends RecyclerView.Adapter<ReguestComment_RateAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<RestaurantReviewsData> commentDataList = new ArrayList<>();

    public ReguestComment_RateAdapter(Context context, Activity activity, List<RestaurantReviewsData> commentDataList) {
        this.context = context;
        this.activity = activity;
        this.commentDataList = commentDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_and_rate_reguest,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
//        HelperMethod.onLoadImageFromUrl(holder.ImageCommentAndRate, commentDataList.get(position).getRestaurant().getPhotoUrl(), context);
        holder.DescriptionCommentAndRate.setText(commentDataList.get(position).getComment());
        holder.nameCommentAndRate.setText(commentDataList.get(position).getClient().getName());
        if (commentDataList.get(position).getRate().equals("1")) {
            holder.ImageCommentAndRate.setImageResource(R.drawable.angry_active1);
        } else if (commentDataList.get(position).getRate().equals("2")) {
            holder.ImageCommentAndRate.setImageResource(R.drawable.sad_active2);

        } else if (commentDataList.get(position).getRate().equals("3")) {
            holder.ImageCommentAndRate.setImageResource(R.drawable.smiling_active3);

        } else if (commentDataList.get(position).getRate().equals("4")) {
            holder.ImageCommentAndRate.setImageResource(R.drawable.smiley_active4);

        } else if (commentDataList.get(position).getRate().equals("5")) {
            holder.ImageCommentAndRate.setImageResource(R.drawable.love_active5);

        }
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commentDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.name_comment_and_rate)
        TextView nameCommentAndRate;
        @BindView(R.id.Description_comment_and_rate)
        TextView DescriptionCommentAndRate;
        @BindView(R.id.Image_comment_and_rate)
        ImageView ImageCommentAndRate;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
