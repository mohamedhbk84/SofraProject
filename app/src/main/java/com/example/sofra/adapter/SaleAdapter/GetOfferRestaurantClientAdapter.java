package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.model.getOffersClient.DataItemOffers;
import com.example.sofra.data.model.getOffersClient.GetOffersClient;
import com.example.sofra.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetOfferRestaurantClientAdapter extends RecyclerView.Adapter<GetOfferRestaurantClientAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<DataItemOffers> offerList = new ArrayList<>();

    public GetOfferRestaurantClientAdapter(Context context, Activity activity, List<DataItemOffers> offerList) {
        this.context = context;
        this.activity = activity;
        this.offerList = offerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.get_offerrestaurant_client_adapter_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.TxtGetTitle.setText(offerList.get(position).getDescription());
        HelperMethod.onLoadImageFromUrl(holder.getAdapterNewOfferImgPhoto,offerList.get(position).getPhotoUrl(),context);
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    @OnClick(R.id.Buttton_getOffer)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.Txt_getTitle)
        TextView TxtGetTitle;
        @BindView(R.id.Buttton_getOffer)
        Button ButttonGetOffer;
        @BindView(R.id.getAdapterNewOfferImgPhoto)
        ImageView getAdapterNewOfferImgPhoto;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
