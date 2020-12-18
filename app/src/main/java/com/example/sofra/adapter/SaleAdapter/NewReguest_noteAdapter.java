package com.example.sofra.adapter.SaleAdapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.acceptOrder.AcceptOrder;
import com.example.sofra.data.model.restaurant_reject_order.RestaurantRejectOrder;
import com.example.sofra.data.model.restaurantmyorders.Datum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReguest_noteAdapter extends RecyclerView.Adapter<NewReguest_noteAdapter.ViewHolder> {


    private final String Type;
    private Context context;
    private static final Integer CALL = 0x2;
    private Activity activity;
    private List<Datum> neworder = new ArrayList<>();
    private ApiServicesSale apiServices;
    private int id_order = 2;

    public NewReguest_noteAdapter(Context context, Activity activity, List<Datum> neworder, String type) {
        this.context = context;
        this.activity = activity;
        this.neworder = neworder;
        this.Type=type;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newreguests_note_gray,
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
        holder.NewReguestTxtName.setText(neworder.get(position).getAddress());
        holder.NewReguestTxtAddress.setText(neworder.get(position).getAddress());
        holder.NewReguestTxtNumber.setText(neworder.get(position).getClientId());
        holder.NewReguestTxtTotalMoney.setText(neworder.get(position).getCost());

        holder.DetailsOrderBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reject();
                neworder.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, neworder.size());

            }
        });



        // HelperMethod.onLoadImageFromUrl(holder.NewReguestImgView,neworder.get(position).g,context);
holder.DetailsOrderBtnConnect.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + neworder.get(position).getClient().getPhone()));
                context.startActivity(callIntent);

            } else {
                permision(CALL);
            }

        } catch (Exception e) {
            Log.e("Demo application", "Failed to invoke call", e);
        }
    }
});
  holder.DetailsOrderBtnAccept.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          apiServices.acceptOrder("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx",id_order).enqueue(new Callback<AcceptOrder>() {
              @Override
              public void onResponse(Call<AcceptOrder> call, Response<AcceptOrder> response) {
                  if (response.body().getStatus()==1) {
                      neworder.remove(position);
                      notifyItemRemoved(position);
                      notifyItemRangeChanged(position, neworder.size());

                  }
              }

              @Override
              public void onFailure(Call<AcceptOrder> call, Throwable t) {
                  Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
              }
          });
      }
  });


    }

    private void setAction(ViewHolder holder, int position) {


        if(Type=="pending"){

        }else if(Type=="current"){
            holder.DetailsOrderBtnExit.setVisibility(View.GONE);
        }
        else if (Type == "completed"){
            holder.DetailsOrderBtnExit.setVisibility(View.GONE);
            holder.DetailsOrderBtnConnect.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return neworder.size();
    }

    @OnClick({R.id.Details_Order_Btn_connect, R.id.Details_Order_Btn_accept, R.id.Details_Order_Btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Details_Order_Btn_connect:
                break;
            case R.id.Details_Order_Btn_accept:
                break;
            case R.id.Details_Order_Btn_exit:
             //   Reject();
//                neworder.remove(view.getId());
                break;
        }
    }

    private void Reject() {
        apiServices.reject("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx",25).enqueue(new Callback<RestaurantRejectOrder>() {
            @Override
            public void onResponse(Call<RestaurantRejectOrder> call, Response<RestaurantRejectOrder> response) {
                if (response.body().getStatus()==1) {
                    try {


                        Toast.makeText(activity,response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantRejectOrder> call, Throwable t) {

            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.New_Reguest_Img_View)
        ImageView NewReguestImgView;
        @BindView(R.id.Txt_three)
        TextView TxtThree;
        @BindView(R.id.New_Reguest_Txt_Name)
        TextView NewReguestTxtName;
        @BindView(R.id.Textthrooo)
        TextView Textthrooo;
        @BindView(R.id.New_Reguest_Txt_Number)
        TextView NewReguestTxtNumber;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.New_Reguest_Txt_Total_Money)
        TextView NewReguestTxtTotalMoney;
        @BindView(R.id.New_Reguest_Txt_address)
        TextView NewReguestTxtAddress;
        @BindView(R.id.Details_Order_Btn_connect)
        Button DetailsOrderBtnConnect;
        @BindView(R.id.Details_Order_Btn_accept)
        Button DetailsOrderBtnAccept;
        @BindView(R.id.Details_Order_Btn_exit)
        Button DetailsOrderBtnExit;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }


    private void permision(Integer requestCode) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, requestCode);

            } else {

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, requestCode);

            }
        } else {

            Toast.makeText(context, "" + Manifest.permission.CALL_PHONE + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


}
