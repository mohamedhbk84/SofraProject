package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.model.restaurants.Datum;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;
import com.example.sofra.ui.Fragment.ReguestFragment.Rguest_Note_OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Reguest_RestauranName_DetailsAdapter extends RecyclerView.Adapter<Reguest_RestauranName_DetailsAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<Datum> restaurantNameList = new ArrayList<>();

    public Reguest_RestauranName_DetailsAdapter(Context context, Activity activity, List<Datum> restaurantNameList) {
        this.context = context;
        this.activity = activity;
        this.restaurantNameList = restaurantNameList;
    }

    public Reguest_RestauranName_DetailsAdapter(List<Datum> restaurantNameList, FragmentActivity activity) {
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reguest_show_details_order_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, final int position) {
        HelperMethod.onLoadImageFromUrl(holder.imageView, restaurantNameList.get(position).getPhoto(), context);
        holder.ReguestName.setText(restaurantNameList.get(position).getName());
        holder.LowDetailsGrayOrder.setText(restaurantNameList.get(position).getDeliveryCost());
        holder.DetailsGrayDelivery.setText(restaurantNameList.get(position).getMinimumCharger());
        holder.open.setText(restaurantNameList.get(position).getAvailability());
        //  holder.RatingBar.setNumStars(restaurantNameList.get(position).getRate());
        holder.RatingBar.setRating(restaurantNameList.get(position).getRate());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.RelativeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reguest_Cycle_detailsActivity Activity =(Reguest_Cycle_detailsActivity)activity;
                Rguest_Note_OrderFragment rguest_note_orderFragment = new Rguest_Note_OrderFragment();
                rguest_note_orderFragment.ResturantItem =restaurantNameList.get(position);
                HelperMethod.replace(rguest_note_orderFragment,Activity.getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);


            }
        });
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return restaurantNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.imageView)
        CircleImageView imageView;
        @BindView(R.id.Reguest_Name)
        TextView ReguestName;
        @BindView(R.id.Rating_Bar)
        android.widget.RatingBar RatingBar;
        @BindView(R.id.Low_Details_gray_Order)
        TextView LowDetailsGrayOrder;
        @BindView(R.id.lm)
        LinearLayout lm;
        @BindView(R.id.Details_gray_Delivery)
        TextView DetailsGrayDelivery;
        @BindView(R.id.Relative_Click)
        RelativeLayout RelativeClick;
        @BindView(R.id.open)
        TextView open;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
