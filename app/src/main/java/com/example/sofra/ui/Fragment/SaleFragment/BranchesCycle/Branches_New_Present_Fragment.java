package com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.NewOfferSaleAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.itemFood.ItemFoodData;
import com.example.sofra.data.model.offers.Offers;
//import com.example.sofra.data.model.restaurant_my_offers.Datum;
import com.example.sofra.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Branches_New_Present_Fragment extends Fragment {


    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    @BindView(R.id.New_Present_Btn_Add)
    Button NewPresentBtnAdd;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private String api_token;

    private List<ItemFoodData> offers = new ArrayList<>();
    private NewOfferSaleAdapter newOfferSaleAdapter;

    private int page = 1;

    public Branches_New_Present_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branches__new__present_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        // api_token = SharedPreferenceManager.LoadData(getActivity(), "USER_API_TOKEN");
        getAllPosts();
        setupRecycler();
        return view;
    }

    private void getAllPosts() {
        apiServices.getallOffers("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx", page).enqueue(new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                if (response.body().getStatus() == 1) {
                    try {

                        offers.addAll(response.body().getData().getData());
                        newOfferSaleAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), response.body().getData().toString(), Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {

            }
        });
    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
        newOfferSaleAdapter = new NewOfferSaleAdapter(getContext(), getActivity(), offers);
        RecyclerView.setAdapter(newOfferSaleAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.New_Present_Btn_Add)
    public void onViewClicked() {
        AddNewOFFerFragment addNewOFFerFragment = new AddNewOFFerFragment();
        HelperMethod.replace(addNewOFFerFragment, getActivity().getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
    }
}
