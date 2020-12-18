package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sofra.R;
import com.example.sofra.data.model.itemFood.ItemFoodData;
//import com.example.sofra.data.model.restaurant_my_offers.Datum;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.EditNewOFFerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewOfferSaleAdapter extends RecyclerView.Adapter<NewOfferSaleAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ItemFoodData> offers = new ArrayList<>();

    public NewOfferSaleAdapter(Context context, Activity activity, List<ItemFoodData> offers) {
        this.context = context;
        this.activity = activity;
        this.offers = offers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newpresent_branches_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, final int position) {
        holder.NewPresentTxt.setText(offers.get(position).getDescription());
        HelperMethod.onLoadImageFromUrl(holder.imageView, offers.get(position).getPhotoUrl(), context);

        holder.profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home_Sale_Cycle_Activty Activity = (Home_Sale_Cycle_Activty) activity;

                EditNewOFFerFragment editNewOFFerFragment = new EditNewOFFerFragment();

                editNewOFFerFragment.RestaurantNewOffer = offers.get(position);
                HelperMethod.replace(editNewOFFerFragment, Activity.getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offers.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, offers.size());

            }
        });
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    @OnClick({R.id.delete_button, R.id.profile_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_button:
//                offers.remove(view.getId());

                break;
            case R.id.profile_button:
                break;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.delete_button)
        ImageButton deleteButton;
        @BindView(R.id.profile_button)
        ImageButton profileButton;
        @BindView(R.id.imageView)
        CircleImageView imageView;
        @BindView(R.id.NewPresent_Txt)
        EditText NewPresentTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
