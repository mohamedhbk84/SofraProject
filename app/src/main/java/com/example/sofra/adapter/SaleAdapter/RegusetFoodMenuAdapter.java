package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sofra.R;
//import com.example.sofra.data.model.items.Datum;
import com.example.sofra.data.model.itemFood.ItemFoodData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.Private_OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegusetFoodMenuAdapter extends RecyclerView.Adapter<RegusetFoodMenuAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ItemFoodData> foodmenu = new ArrayList<>();

    public RegusetFoodMenuAdapter(Context context, Activity activity, List<ItemFoodData> foodmenu) {
        this.context = context;
        this.activity = activity;
        this.foodmenu = foodmenu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.foodmenu_reguest_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

    }

    private void setAction(ViewHolder holder, final int position) {
        holder.NameFoodmenu.setText(foodmenu.get(position).getName());
        holder.name.setText(foodmenu.get(position).getDescription());
        holder.saleNumber.setText(foodmenu.get(position).getPrice());
        HelperMethod.onLoadImageFromUrl(holder.ImageViewFoodMenu, foodmenu.get(position).getPhotoUrl(), context);
        holder.ImageViewFoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reguest_Cycle_detailsActivity Activity=(Reguest_Cycle_detailsActivity) activity;
                Private_OrderFragment private_orderFragment = new Private_OrderFragment();
                private_orderFragment.RestaurantItem = foodmenu.get(position);
                HelperMethod.replace(private_orderFragment,Activity.getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);

            }
        });


    }

    @Override
    public int getItemCount() {
        return foodmenu.size();
    }

    @OnClick({R.id.Image_View_Food_menu, R.id.Linear})
    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.Image_View_Food_menu:
//                Home_Sale_Cycle_Activty Activity=(Home_Sale_Cycle_Activty) activity;
//                Private_OrderFragment private_orderFragment = new Private_OrderFragment();
//                HelperMethod.replace(private_orderFragment,Activity.getSupportFragmentManager(),R.id.Home_Sale_Frame_Container,null,null);
//
//                break;
//            case R.id.Linear:
//
//                break;
//        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.Image_View_Food_menu)
        ImageButton ImageViewFoodMenu;
        @BindView(R.id.Name_Foodmenu)
        TextView NameFoodmenu;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sale)
        TextView sale;
        @BindView(R.id.sale_number)
        TextView saleNumber;

        @BindView(R.id.Linear)
        LinearLayout Linear;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
