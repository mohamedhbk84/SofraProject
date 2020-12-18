package com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle;


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
import com.example.sofra.adapter.SaleAdapter.ReguestComment_RateAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.restaurant_reviews.RestaurantReviews;
import com.example.sofra.data.model.restaurant_reviews.RestaurantReviewsData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.RoomDatabase.Add_Note_about_AppFragment;

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
public class Reguest_Comment_and_Rate_Fragment extends Fragment {


    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.Add_comment_and_rate)
    Button AddCommentAndRate;

    Unbinder unbinder;
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    private ApiServicesSale apiServices;
    private ReguestComment_RateAdapter reguestComment_rateAdapter;
    private List<RestaurantReviewsData> commentDataList = new ArrayList<>();
    private int idRestaurant;

    private int Page;
    private int current_Page = 1;

    public Reguest_Comment_and_Rate_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reguest__comment_and__rate_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getCommentAndRate(current_Page);
        setupRecycler();
        return view;
    }

    private void setupRecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
        reguestComment_rateAdapter = new ReguestComment_RateAdapter(getContext(), getActivity(), commentDataList);
        RecyclerView.setAdapter(reguestComment_rateAdapter);
    }

    private void getCommentAndRate(int Page) {
        apiServices.getallComment("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB", idRestaurant, Page).enqueue(new Callback<RestaurantReviews>() {
            @Override
            public void onResponse(Call<RestaurantReviews> call, Response<RestaurantReviews> response) {
                if (response.body().getStatus() == 1) {
                    try {
                        //  ItemFoodPagination data = response.body().getData();

                        commentDataList.addAll(response.body().getData().getData());
                        reguestComment_rateAdapter.notifyDataSetChanged();


                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantReviews> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Add_comment_and_rate)
    public void onViewClicked() {
        Add_Note_about_AppFragment add_note_about_appFragment = new Add_Note_about_AppFragment();
        HelperMethod.replace(add_note_about_appFragment, getActivity().getSupportFragmentManager(), R.id.Frame, null, null);

    }
}
