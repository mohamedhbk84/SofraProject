package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.listoforders.Data;
import com.example.sofra.data.model.listoforders.ListOfOrders;
import com.example.sofra.data.model.listoforders.OrdersData;
import com.example.sofra.data.model.myOrders.Datum;
import com.example.sofra.data.model.rejectOrder.RejectOrder;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.Customer_CurrentRequests_noteFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.Customer_Home_TabFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.Customer_PreviousRequests_noteFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReguestOrderAcceptOrRejectFoodAdapter extends RecyclerView.Adapter<ReguestOrderAcceptOrRejectFoodAdapter.ViewHolder> {

    private Customer_Home_TabFragment customer_home_tabFragment;

    private final String Type;
    private Context context;
    private Activity activity;
    private List<Datum> ListOrder = new ArrayList<>();
    private ApiServicesSale apiServices;
    private int id = 161;


    public ReguestOrderAcceptOrRejectFoodAdapter(Context context, Activity activity, List<Datum> ListOrder, String type) {
        this.context = context;
        this.activity = activity;
        this.ListOrder = ListOrder;
        this.Type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reguest_order_view_pager_gray,
                parent, false);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);


    }

    private void setData(ViewHolder holder, final int position) {
        HelperMethod.onLoadImageFromUrl(holder.OrderImage, ListOrder.get(position).getPhotoUrl(), context);
        holder.OrderName.setText(ListOrder.get(position).getDescription());
        holder.orderNumber.setText(ListOrder.get(position).getRestaurantId());
        holder.orderMoney.setText(ListOrder.get(position).getCost());

    }

    private void setAction(ViewHolder holder, final int position) {




        if (Type == "current") {
            holder.ButtonAccept.setVisibility(View.VISIBLE);
            holder.ButtonReject.setVisibility(View.VISIBLE);

        } else if (Type == "completed") {
            holder.ButtonAccept.setVisibility(View.INVISIBLE);
            holder.ButtonReject.setVisibility(View.INVISIBLE);
        }

        if (Type == "current") {
            final Integer Id = ListOrder.get(position).getId();
            holder.ButtonReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    apiServices.rejectOrder("kPXTlvfDzgfXJUvFSosVt1CqkHUdumPxc4SZ7nbM0hqnOBAwRpuyQyOQO87V",Id).enqueue(new Callback<RejectOrder>() {
                        @Override
                        public void onResponse(Call<RejectOrder> call, Response<RejectOrder> response) {
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                ListOrder.remove(position);
                            }
                        }

                        @Override
                        public void onFailure(Call<RejectOrder> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            holder.ButtonAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer Id = ListOrder.get(position).getId();

                    apiServices.confirmOrder("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB", Id).enqueue(new Callback<RejectOrder>() {
                        @Override
                        public void onResponse(Call<RejectOrder> call, Response<RejectOrder> response) {
                            if (response.body().getStatus() == 1) {

                                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<RejectOrder> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return ListOrder.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.Order_Image)
        CircleImageView OrderImage;
        @BindView(R.id.Order_Name)
        TextView OrderName;
        @BindView(R.id.order_number)
        TextView orderNumber;

        @BindView(R.id.order_money)
        TextView orderMoney;

        @BindView(R.id.Button_Accept)
        Button ButtonAccept;
        @BindView(R.id.Button_Reject)
        Button ButtonReject;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
