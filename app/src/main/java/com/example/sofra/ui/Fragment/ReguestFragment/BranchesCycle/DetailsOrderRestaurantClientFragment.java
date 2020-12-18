package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.ItemsListOrderAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.showOrder.Data;
import com.example.sofra.data.model.showOrder.ShowOrder;
import com.example.sofra.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsOrderRestaurantClientFragment extends Fragment {


    @BindView(R.id.showOrderRestaurantClientFragmentImgPhotoRestaurant)
    ImageView showOrderRestaurantClientFragmentImgPhotoRestaurant;
    @BindView(R.id.showOrderRestaurantClientFragmentTvNameRestaurant)
    TextView showOrderRestaurantClientFragmentTvNameRestaurant;
    @BindView(R.id.showOrderRestaurantClientFragmentTvDateOrderRestaurant)
    TextView showOrderRestaurantClientFragmentTvDateOrderRestaurant;
    @BindView(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @BindView(R.id.showOrderRestaurantClientFragmentTvAddressRestaurant)
    TextView showOrderRestaurantClientFragmentTvAddressRestaurant;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.showOrderRestaurantClientFragmentTvOrderDetailsRestaurant)
    ListView showOrderRestaurantClientFragmentTvOrderDetailsRestaurant;
    @BindView(R.id.showOrderRestaurantClientFragmentTvPriceRestaurant)
    TextView showOrderRestaurantClientFragmentTvPriceRestaurant;
    @BindView(R.id.showOrderRestaurantClientFragmentTvDeliveryCostRestaurant)
    TextView showOrderRestaurantClientFragmentTvDeliveryCostRestaurant;
    @BindView(R.id.showOrderRestaurantClientFragmentTvTotalRestaurant)
    TextView showOrderRestaurantClientFragmentTvTotalRestaurant;
    @BindView(R.id.showOrderRestaurantClientFragmentTvPayingRestaurant)
    TextView showOrderRestaurantClientFragmentTvPayingRestaurant;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private int id;

    public DetailsOrderRestaurantClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_order_restaurant_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
       showOrder();
        return view;
    }

    private void showOrder() {
apiServices.ShowOrder("6s9myYlaDULl8Cb78qWAdSwyArssyh4QWIyfaU6l5miUvOou5iS6QMjMi00v",id).enqueue(new Callback<ShowOrder>() {
    @Override
    public void onResponse(Call<ShowOrder> call, Response<ShowOrder> response) {
        if (response.body().getStatus()==1) {
            Data data = response.body().getData();
            HelperMethod.onLoadImageFromUrl(showOrderRestaurantClientFragmentImgPhotoRestaurant,data.getRestaurant().getPhoto(),getContext());
            showOrderRestaurantClientFragmentTvNameRestaurant.setText(response.body().getData().getRestaurant().getName());
            showOrderRestaurantClientFragmentTvDateOrderRestaurant.setText(response.body().getData().getUpdatedAt());
            showOrderRestaurantClientFragmentTvAddressRestaurant.setText(response.body().getData().getAddress());
            showOrderRestaurantClientFragmentTvPriceRestaurant.setText(response.body().getData().getCost());
            showOrderRestaurantClientFragmentTvDeliveryCostRestaurant.setText(response.body().getData().getDeliveryCost());
            showOrderRestaurantClientFragmentTvTotalRestaurant.setText(response.body().getData().getTotal());

            if (response.body().getData().getPaymentMethodId().equals("1")) {
                showOrderRestaurantClientFragmentTvPayingRestaurant.setText("cash");
            }else {
                showOrderRestaurantClientFragmentTvPayingRestaurant.setText("Card_paying");
            }

            ItemsListOrderAdapter adapterListItemsOrder = new ItemsListOrderAdapter(getContext(), response.body().getData().getItems());
            showOrderRestaurantClientFragmentTvOrderDetailsRestaurant.setAdapter(adapterListItemsOrder);



        }
    }

    @Override
    public void onFailure(Call<ShowOrder> call, Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
