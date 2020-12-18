package com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.DetailsOrderSaleAdapter;
import com.example.sofra.adapter.SaleAdapter.RegusetFoodMenuAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.itemFood.ItemFood;
import com.example.sofra.data.model.itemFood.ItemFoodData;
//import com.example.sofra.data.model.items.Datum;
//import com.example.sofra.data.model.items.Items;

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
public class Reguest_Show_FoodmenuFragment extends Fragment {


    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private List<ItemFoodData> foodmenu = new ArrayList<>();
    private RegusetFoodMenuAdapter regusetFoodMenuAdapter;

    public Reguest_Show_FoodmenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reguest__show__foodmenu, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getallmenu();
        setupRecycler();
        return view;
    }

    private void getallmenu() {
        apiServices.getAllFoodmenu(6).enqueue(new Callback<ItemFood>() {
            @Override
            public void onResponse(Call<ItemFood> call, Response<ItemFood> response) {
                if (response.body().getStatus()==1) {

                    try {
                        List<ItemFoodData> data = response.body().getData().getData();
                        foodmenu.addAll(data);
                        regusetFoodMenuAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ItemFood> call, Throwable t) {

            }
        });
    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
       regusetFoodMenuAdapter = new RegusetFoodMenuAdapter(getContext(), getActivity(), foodmenu);
        RecyclerView.setAdapter(regusetFoodMenuAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
