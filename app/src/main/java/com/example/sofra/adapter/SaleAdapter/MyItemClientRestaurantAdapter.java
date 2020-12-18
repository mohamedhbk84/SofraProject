package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.model.itemFood.ItemFoodData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.ConfirmOrderDetailsClientFragment;
import com.example.sofra.ui.RoomDatabase.RoomManger;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyItemClientRestaurantAdapter extends RecyclerView.Adapter<MyItemClientRestaurantAdapter.ViewHolder> {
    public  MyItemClientRestaurantAdapter myItemClientRestaurantAdapter;
    public  Reguest_Cycle_detailsActivity reguest_cycle_detailsActivity;
    public List<ItemFoodData> itemFoodDataList = new ArrayList<>();

    private Context context;
    private Activity activity;
    private int quantity;
    public static Integer IdRestaurant;
    private RoomManger roomManger;

    public MyItemClientRestaurantAdapter(List<ItemFoodData> itemFoodDataList, Activity context, Reguest_Cycle_detailsActivity reguest_cycle_detailsActivity) {
        this.itemFoodDataList = itemFoodDataList;
        this.context = context;
        this.reguest_cycle_detailsActivity = reguest_cycle_detailsActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inshare_private_order_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(final ViewHolder holder, final int position) {
        holder.NameOrder.setText(itemFoodDataList.get(position).getName());
//        holder.ShowCount.setText("" + itemFoodDataList.get(position).getQuantity());
        holder.setAdapterCartTvDetailsItem.setText(itemFoodDataList.get(position).getDescription());
        holder.DetailsOrderRecyclerTxtMoney.setText(itemFoodDataList.get(position).getPrice());
        HelperMethod.onLoadImageFromUrl(holder.DetailsOrderRecyclerImage, itemFoodDataList.get(position).getPhotoUrl(), context);

        IdRestaurant = Integer.valueOf(itemFoodDataList.get(position).getRestaurantId());
        Log.d("getIdRestaurant", String.valueOf(IdRestaurant));
        holder.mainaseTheNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.ShowCount.getText().toString());
                quantity++;
                holder.ShowCount.setText("" + quantity);
//                roomManger.roomDao().update(itemFoodDataList.get(position).getItemId(),quantity);

            }
        });
        holder.PossitiveTheNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.ShowCount.getText().toString());
                quantity--;
                holder.ShowCount.setText("" + quantity);
//                roomManger.roomDao().update(itemFoodDataList.get(position).getItemId(),quantity);
            }
        });
        holder.ImageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 roomManger.roomDao().onDelete(itemFoodDataList.get(position).getItemId());
                 itemFoodDataList.remove(position);
                 reguest_cycle_detailsActivity.inShare_private_orderFragment.myItemClientRestaurantAdapter.notifyDataSetChanged();

                if (itemFoodDataList.isEmpty()){
                    roomManger.roomDao().deleteAll();
                }
                 //                itemFoodDataList.remove(position);
//                notifyItemRemoved(position);
//                notifyDataSetChanged();
            }
        });
    }

    private void setAction(ViewHolder holder, int position) {

    }


    public double getTotal() {
        double AllTotal = 0;
        try {

            if (itemFoodDataList.isEmpty())
                return AllTotal;

            for (int i = 0; i <  roomManger.roomDao().getAll().size(); i++) {
                AllTotal = AllTotal + Double.valueOf( roomManger.roomDao().getAll().get(i).getPrice()) * Double.valueOf( roomManger.roomDao().getAll().get(i).getQuantity());
            }

        }catch (Exception e){
            e.getMessage();
        }

        return AllTotal;
    }


    public double getTotal(double quantity) {
        double AllTotal = 0;

        try {

            for (int i = 0; i < itemFoodDataList.size(); i++) {

                AllTotal = AllTotal + Double.valueOf(itemFoodDataList.get(i).getPrice()) * quantity ;
            }

        }catch (Exception e){
            e.getMessage();
        }

        return AllTotal;
    }





    @Override
    public int getItemCount() {
        return itemFoodDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.Details_Order_Recycler_Image)
        PorterShapeImageView DetailsOrderRecyclerImage;
        @BindView(R.id.setAdapterCartTvDetailsItem)
        TextView setAdapterCartTvDetailsItem;
        @BindView(R.id.Name_Order)
        TextView NameOrder;
        @BindView(R.id.Details_Order_Recycler_Txt_money)
        TextView DetailsOrderRecyclerTxtMoney;
        @BindView(R.id.Show_Count)
        TextView ShowCount;
        @BindView(R.id.mainase_the_number)
        ImageButton mainaseTheNumber;
        @BindView(R.id.Possitive_the_number)
        ImageButton PossitiveTheNumber;
        @BindView(R.id.Image_Exit)
        ImageView ImageExit;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
