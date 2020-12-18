package com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle;


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
import com.example.sofra.data.model.restaurantcommissions.Data;
import com.example.sofra.data.model.restaurantcommissions.RestaurantCommissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Branches_CommissionFragment extends Fragment {

    @BindView(R.id.Txt_Commision)
    TextView TxtCommision;
    @BindView(R.id.Branche_commission_txt_details)
    TextView BrancheCommissionTxtDetails;
    @BindView(R.id.Money_Txt_SaleFood)
    TextView MoneyTxtSaleFood;
    @BindView(R.id.l1)
    LinearLayout l1;
    @BindView(R.id.Money_Txt_CommissionApp)
    TextView MoneyTxtCommissionApp;
    @BindView(R.id.l2)
    LinearLayout l2;
    @BindView(R.id.Money_Txt_paidMoney)
    TextView MoneyTxtPaidMoney;
    @BindView(R.id.l3)
    LinearLayout l3;
    @BindView(R.id.Money_Txt_Offer)
    TextView MoneyTxtOffer;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.Name)
    TextView Name;
    @BindView(R.id.lll1)
    LinearLayout lll1;
    @BindView(R.id.Name2)
    TextView Name2;
    @BindView(R.id.llll1)
    LinearLayout llll1;
    private ApiServicesSale apiServices;
    private Unbinder unbinder;

    public Branches_CommissionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branches__commission, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getcomission();
        return view;
    }

    private void getcomission() {
        apiServices.commision("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx").enqueue(new Callback<RestaurantCommissions>() {
            @Override
            public void onResponse(Call<RestaurantCommissions> call, Response<RestaurantCommissions> response) {
                if (response.body().getStatus() == 1) {
                    try {
                        Data data = response.body().getData();

                        MoneyTxtOffer.setText(data.getTotal());
                        MoneyTxtSaleFood.setText(data.getPayments());
                        MoneyTxtCommissionApp.setText(data.getTotal());
                        MoneyTxtPaidMoney.setText(data.getCount());
                        Name.setText(data.getNetCommissions());
                        Name2.setText(data.getCommission());
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantCommissions> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
