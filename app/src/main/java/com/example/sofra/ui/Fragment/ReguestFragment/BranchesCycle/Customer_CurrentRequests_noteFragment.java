package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.ReguestOrderAcceptOrRejectFoodAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.listoforders.ListOfOrders;
import com.example.sofra.data.model.listoforders.OrdersData;
import com.example.sofra.data.model.myOrders.Datum;
import com.example.sofra.data.model.myOrders.MyOrders;

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
public class Customer_CurrentRequests_noteFragment extends Fragment {


    @BindView(R.id.RecyclerView_Current)
    RecyclerView RecyclerViewCurrent;

    private ReguestOrderAcceptOrRejectFoodAdapter reguestOrderAcceptOrRejectFoodAdapter;
    private ApiServicesSale apiServices;
    private List<Datum> ListOrder = new ArrayList<>();
    private String Type = "Current";
    private int page = 1;
    Unbinder unbinder;
    public Customer_CurrentRequests_noteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer__current_requests_note, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);

        getAllListOrder();
        setupRecycler();
        return view;
    }

    private void getAllListOrder() {
        apiServices.getListOfOrder("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB",
                "Current",page)
                .enqueue(new Callback<ListOfOrders>() {
                    @Override
                    public void onResponse(Call<ListOfOrders> call, Response<ListOfOrders> response) {
                        if (response.body().getStatus() ==1) {
                            try{
                                List<Datum> data = response.body().getData().getData();
                                ListOrder.addAll(data);
                                reguestOrderAcceptOrRejectFoodAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }catch (Exception e){
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ListOfOrders> call, Throwable t) {

                    }
                });
    }
    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerViewCurrent.setLayoutManager(mang);
        reguestOrderAcceptOrRejectFoodAdapter = new ReguestOrderAcceptOrRejectFoodAdapter(getContext(),getActivity(),ListOrder, Type);
        RecyclerViewCurrent.setAdapter(reguestOrderAcceptOrRejectFoodAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
