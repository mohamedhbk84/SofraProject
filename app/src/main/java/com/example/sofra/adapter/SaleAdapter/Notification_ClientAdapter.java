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
import com.example.sofra.data.model.notifications.DataNotify;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Notification_ClientAdapter extends RecyclerView.Adapter<Notification_ClientAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<DataNotify> notification_data = new ArrayList<>();

    public Notification_ClientAdapter(Context context, Activity activity, List<DataNotify> notification_data) {
        this.context = context;
        this.activity = activity;
        this.notification_data = notification_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_adapter_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.notificationAdapterTitleTxt.setText(notification_data.get(position).getTitle());
        holder.notificationAdapterTimeTxt.setText(notification_data.get(position).getUpdatedAt());
//        if (notification_data.get(position).getNotifiableId().equals("0")) {
//            holder.notificationAdapter.setImageResource(R.mipmap.alert);
//        } else {
//            holder.notificationAdapter.setImageResource(R.drawable.icon_un_notify);
//        }
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notification_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.notificationAdapterTimeTxt)
        TextView notificationAdapterTimeTxt;
        @BindView(R.id.notificationAdapterTitleTxt)
        TextView notificationAdapterTitleTxt;
        @BindView(R.id.notification_adapter)
        ImageView notificationAdapter;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
