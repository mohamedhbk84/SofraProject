package com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.Reguest_RestauranName_DetailsAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.cities.Cities;
import com.example.sofra.data.model.cities1.Cities1;

import com.example.sofra.data.model.restaurants.Datum;
import com.example.sofra.data.model.restaurants.Restaurants;

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
public class Reguest_Show_Details_OrderFragment extends Fragment {


    @BindView(R.id.city_id)
    Spinner cityId;
    @BindView(R.id.the_food_favourit)
    EditText theFoodFavourit;
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    Unbinder unbinder;
    @BindView(R.id.IMage_Search_Filter)
    ImageView IMageSearchFilter;
    private ApiServicesSale apiServices;
    private List<Datum> restaurantNameList = new ArrayList<>();
    private int page = 1;
    private int idCity;
    private Reguest_RestauranName_DetailsAdapter reguest_restauranName_detailsAdapter;

    List<String> citiesnames = new ArrayList<>();
    List<Integer> citiesids = new ArrayList<>();
    //    List<String> categorynames = new ArrayList<>();
//    List<Integer> categoryids = new ArrayList<>();
    private int id = 1;
    private boolean checkFilterPost = true;

    public Reguest_Show_Details_OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reguest__show__details__order, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getAllRestaurant(page);
        setupRecycler();
        getCity();
//        getCategory();
        return view;
    }

//    private void getCategory() {
//        apiServices.getcategories().enqueue(new Callback<GeneralObject>() {
//            @Override
//            public void onResponse(Call<GeneralObject> call, Response<GeneralObject> response) {
//                if (response.body().getStatus()==1) {
//                    categorynames.add("ابحث عن مطعمك المفضل ");
//                    categoryids.add(0);
//                    for (int i = 0; i <response.body().getData().size() ; i++) {
//
//
//                        categorynames.add(response.body().getData().get(i).getName());
//                        categoryids.add(response.body().getData().get(i).getId());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categorynames);
//                    theFoodFavourit.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GeneralObject> call, Throwable t) {
//
//            }
//        });
//    }

    private void getCity() {
        apiServices.getcity().enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {

                try {
                    if (response.body().getStatus() == 1) {

                        citiesnames.add("المدينة");
                        citiesids.add(0);
                        for (int i = 0; i < response.body().getData().getData().size(); i++) {

                            citiesnames.add(response.body().getData().getData().get(i).getName());
                            citiesids.add(response.body().getData().getData().get(i).getId());
                            Toast.makeText(getActivity(),  response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        {

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, citiesnames);

                            cityId.setAdapter(adapter);
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });
    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
        reguest_restauranName_detailsAdapter = new Reguest_RestauranName_DetailsAdapter(getContext(), getActivity(), restaurantNameList);
        RecyclerView.setAdapter(reguest_restauranName_detailsAdapter);

    }

    private void getAllRestaurant(int page) {
        apiServices.getAllRestaurants(theFoodFavourit.getText().toString(), idCity, page).enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                if (response.body().getStatus() == 1) {

                    try {

                        List<Datum> data = response.body().getData().getData();
                        restaurantNameList.addAll(data);
                        reguest_restauranName_detailsAdapter.notifyDataSetChanged();

                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.IMage_Search_Filter)
    public void onViewClicked() {
        showfilter();
    }

    private void showfilter() {


        if (cityId.getSelectedItemPosition() ==
                0 && theFoodFavourit.getText().toString().isEmpty() && !checkFilterPost) {

            reguest_restauranName_detailsAdapter = new Reguest_RestauranName_DetailsAdapter(restaurantNameList, getActivity());
            RecyclerView.setAdapter(reguest_restauranName_detailsAdapter);

            checkFilterPost = true;

        } else {

            reguest_restauranName_detailsAdapter = new Reguest_RestauranName_DetailsAdapter(restaurantNameList, getActivity());
            RecyclerView.setAdapter(reguest_restauranName_detailsAdapter);


            checkFilterPost = false;

            getAllRestaurant(1);


//        apiServices.getAllFilter(id).enqueue(new Callback<Restaurants>() {
//            @Override
//            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
//                 if (response.body().getStatus()==1) {
//                     if (page==1) {
//                         restaurantNameList = new ArrayList<>();
//                         reguest_restauranName_detailsAdapter = new Reguest_RestauranName_DetailsAdapter(getContext(),getActivity(),restaurantNameList);
//                         RecyclerView.setAdapter(reguest_restauranName_detailsAdapter);
//                     }
//                    restaurantNameList.addAll(response.body().getData().getData());
//                  reguest_restauranName_detailsAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Restaurants> call, Throwable t) {
//
//            }
//        });
        }
    }
}