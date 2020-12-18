package com.example.sofra.ui.Fragment.SaleFragment.HomeCycle;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.DetailsOrderSaleAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.model.my_item.Datum;
import com.example.sofra.model.my_item.MyItem;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.BranchesAddDetailsFragment;

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
public class Detais_Order_SaleFragment extends Fragment {


    @BindView(R.id.Details_Order_Txt_Show)
    TextView DetailsOrderTxtShow;


    Unbinder unbinder;
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    @BindView(R.id.Home_Fragment_F_m)
    FloatingActionButton HomeFragmentFM;

    private String api_token;
    private ApiServicesSale apiServices;
    private int page = 1;
    private List<Datum> myItems = new ArrayList<>();
    private DetailsOrderSaleAdapter detailsOrderSaleAdapter;

    public Detais_Order_SaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detais__order__sale, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        api_token = SharedPreferenceManager.LoadData(getActivity(), "USER_API_TOKEN");
        getAllDetails();
        setupRecycler();
        return view;

    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
        detailsOrderSaleAdapter = new DetailsOrderSaleAdapter(getContext(), getActivity(), myItems);
        RecyclerView.setAdapter(detailsOrderSaleAdapter);


    }

    private void getAllDetails() {
        apiServices.getAllItems("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx", page).enqueue(new Callback<MyItem>() {
            @Override
            public void onResponse(Call<MyItem> call, Response<MyItem> response) {
                if (response.body().getStatus() == 1) {
                    try {
                        List<Datum> data = response.body().getData().getData();
                        DetailsOrderTxtShow.setText(data.get(page).getRestaurantId());
                        myItems.addAll(data);
                        detailsOrderSaleAdapter.notifyDataSetChanged();


                    } catch (Exception e) {
                        Log.i(e.getMessage(), "data: ");
                    }
                }

            }

            @Override
            public void onFailure(Call<MyItem> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Home_Fragment_F_m)
    public void onViewClicked() {
        BranchesAddDetailsFragment branchesAddDetailsFragment = new BranchesAddDetailsFragment();
        HelperMethod.replace(branchesAddDetailsFragment,getActivity().getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);


    }

}
