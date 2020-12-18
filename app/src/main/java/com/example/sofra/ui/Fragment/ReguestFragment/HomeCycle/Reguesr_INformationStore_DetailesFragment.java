package com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;

import com.example.sofra.data.model.restaurant.RestaurantData;
import com.example.sofra.data.model.restaurant.Restaurant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reguesr_INformationStore_DetailesFragment extends Fragment {


    @BindView(R.id.information_status)
    TextView informationStatus;
    @BindView(R.id.lm)
    LinearLayout lm;
    @BindView(R.id.information_city)
    TextView informationCity;
    @BindView(R.id.llla)
    LinearLayout llla;
    @BindView(R.id.information_Details_City)
    TextView informationDetailsCity;
    @BindView(R.id.lm1)
    LinearLayout lm1;
    @BindView(R.id.information_Details_Below_Money)
    TextView informationDetailsBelowMoney;
    @BindView(R.id.bb)
    LinearLayout bb;
    @BindView(R.id.information_Delivery)
    TextView informationDelivery;
    @BindView(R.id.bba)
    LinearLayout bba;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    public Reguesr_INformationStore_DetailesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reguesr__information_store__detailes, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getdataStore();
        return view;
    }

    private void getdataStore() {
        apiServices.getAllDetails(7).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (response.body().getStatus()==1) {
               try {

                   RestaurantData data = response.body().getData();
                   informationStatus.setText(data.getAvailability());
                   informationCity.setText( data.getRegion().getCity().getName());
                   informationDetailsCity.setText(data.getRegion().getName());
                   informationDetailsBelowMoney.setText(data.getDeliveryCost());
                   informationDelivery.setText(data.getMinimumCharger());
                   Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

               }catch (Exception e){
                   Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
               }
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
