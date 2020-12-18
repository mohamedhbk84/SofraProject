package com.example.sofra.ui.Fragment.SaleFragment.HomeCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sofra.R;

import com.example.sofra.adapter.SaleAdapter.NewReguest_noteAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.restaurantmyorders.Datum;
import com.example.sofra.data.model.restaurantmyorders.RestaurantMyOrders;

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
public class NewRequests_noteFragment extends Fragment {


    @BindView(R.id.New_Reguest_RV)
    RecyclerView NewReguestRV;
    Unbinder unbinder;
    private String api_token;
    private ApiServicesSale apiServices;
    private NewReguest_noteAdapter newReguest_noteAdapter;
    private int page = 1;
    private List<Datum> neworder = new ArrayList<>();
    private String Type="pending";

    public NewRequests_noteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_requests_note, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        api_token = SharedPreferenceManager.LoadData(getActivity(), "USER_API_TOKEN");
        getAllNewReguest();
        setupRecycler();
        return view;
    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        NewReguestRV.setLayoutManager(mang);
        newReguest_noteAdapter = new NewReguest_noteAdapter(getContext(),getActivity(),neworder,Type);
        NewReguestRV.setAdapter(newReguest_noteAdapter);
    }

    private void getAllNewReguest() {
    apiServices.getAllOrders("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx","pending",page).enqueue(new Callback<RestaurantMyOrders>() {
        @Override
        public void onResponse(Call<RestaurantMyOrders> call, Response<RestaurantMyOrders> response) {
            if (response.body().getStatus()==1) {
                try {

                    neworder.addAll(response.body().getData().getData());
                    newReguest_noteAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }

        @Override
        public void onFailure(Call<RestaurantMyOrders> call, Throwable t) {

        }
    });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
