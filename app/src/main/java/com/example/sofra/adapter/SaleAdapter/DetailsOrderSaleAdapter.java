package com.example.sofra.adapter.SaleAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.model.my_item.Datum;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.Branches_getAllDetails_Fragment;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsOrderSaleAdapter extends RecyclerView.Adapter<DetailsOrderSaleAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<Datum> myItems = new ArrayList<>();


    public DetailsOrderSaleAdapter(Context context, Activity activity, List<Datum> myItems) {
        this.context = context;
        this.activity = activity;
        this.myItems = myItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detais_order_salefragment_recycler_gray,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(final ViewHolder holder, final int position) {
        holder.DetailsOrderRecyclerTxtElsanfa.setText(myItems.get(position).getName());
        holder.DetailsOrderRecyclerTxtGenral.setText(myItems.get(position).getDescription());
        holder.DetailsOrderRecyclerTxtElwafa.setText(myItems.get(position).getDescription());
        //   holder.DetailsOrderRecyclerTxtMoney.setText(myItems.get(position).getPrice());
        holder.DetailsOrderRecyclerTxtSaleOrder.setText(myItems.get(position).getPrice());

        HelperMethod.onLoadImageFromUrl(holder.DetailsOrderRecyclerImage, myItems.get(position).getPhotoUrl(), context);
        holder.profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home_Sale_Cycle_Activty Activity = (Home_Sale_Cycle_Activty) activity;

                Branches_getAllDetails_Fragment editOrderFragment = new Branches_getAllDetails_Fragment();

                editOrderFragment.RestaurantNewItem = myItems.get(position);
                HelperMethod.replace(editOrderFragment, Activity.getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);


//        Intent i =new Intent(activity,Branches_Sale_Cycle_Activity.class);
//        activity.startActivity(i);

            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRemoved(position);

            }
        });
    }

    private void setAction(final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @OnClick({R.id.delete_button, R.id.profile_button})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.delete_button:
                myItems.remove(view.getId());
                notifyDataSetChanged();
                break;
            case R.id.profile_button:

                //Branches_getAllDetails_Fragment branches_getAllDetails_fragment = new Branches_getAllDetails_Fragment();
                // HelperMethod.replace(branches_getAllDetails_fragment,, R.id.Branche_Sale_Frame_Container, null, null);

                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.Details_Order_Recycler_Image)
        PorterShapeImageView DetailsOrderRecyclerImage;
        @BindView(R.id.Details_Order_Recycler_Txt_elsanfa)
        TextView DetailsOrderRecyclerTxtElsanfa;
        @BindView(R.id.Details_Order_Recycler_Txt_genral)
        TextView DetailsOrderRecyclerTxtGenral;
        @BindView(R.id.Details_Order_Recycler_Txt_elwafa)
        TextView DetailsOrderRecyclerTxtElwafa;
        @BindView(R.id.Details_Order_Recycler_Txt_SaleOrder)
        TextView DetailsOrderRecyclerTxtSaleOrder;
        @BindView(R.id.Details_Order_Recycler_Txt_money)
        TextView DetailsOrderRecyclerTxtMoney;
        @BindView(R.id.delete_button)
        ImageButton deleteButton;
        @BindView(R.id.profile_button)
        ImageButton profileButton;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
