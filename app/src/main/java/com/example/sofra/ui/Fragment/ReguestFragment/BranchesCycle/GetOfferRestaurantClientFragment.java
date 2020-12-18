package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.GetOfferRestaurantClientAdapter;
import com.example.sofra.adapter.SaleAdapter.ReguestOrderAcceptOrRejectFoodAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.getOffersClient.DataItemOffers;
import com.example.sofra.data.model.getOffersClient.GetOffersClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetOfferRestaurantClientFragment extends Fragment {


    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private List<DataItemOffers> offerList = new ArrayList<>();
    private GetOfferRestaurantClientAdapter getOfferRestaurantClientAdapter;
    private int Current = 0;

    public GetOfferRestaurantClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_offer_restaurant_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getListOffer(Current);
        setupRecycler();
        return view;
    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
        getOfferRestaurantClientAdapter = new GetOfferRestaurantClientAdapter(getContext(), getActivity(), offerList);
        RecyclerView.setAdapter(getOfferRestaurantClientAdapter);
    }

    private void getListOffer(int Page) {
        apiServices.getOffers(Page).enqueue(new Callback<GetOffersClient>() {
            @Override
            public void onResponse(Call<GetOffersClient> call, Response<GetOffersClient> response) {
                if (response.body().getStatus() == 1) {
                    List<DataItemOffers> data = response.body().getData().getData();
                    offerList.addAll(data);
                    getOfferRestaurantClientAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetOffersClient> call, Throwable t) {
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
