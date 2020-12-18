package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.informationRestaurant.Data;
import com.example.sofra.data.model.informationRestaurant.InformationRestaurant;
import com.example.sofra.data.model.newOrder.NewOrder;

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
public class ConfirmOrderDetailsClientFragment extends Fragment {


    @BindView(R.id.txt_Order)
    TextView txtOrder;
    @BindView(R.id.confirmOrderDetailsFragmentClientEtNote)
    EditText confirmOrderDetailsFragmentClientEtNote;
    @BindView(R.id.confirmOrderDetailsFragmentClientTvAddress)
    EditText confirmOrderDetailsFragmentClientTvAddress;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.confirmOrderDetailsFragmentClientRbPayOnline)
    RadioButton confirmOrderDetailsFragmentClientRbPayOnline;
    @BindView(R.id.confirmOrderDetailsFragmentClientRbCash)
    RadioButton confirmOrderDetailsFragmentClientRbCash;
    @BindView(R.id.confirmOrderDetailsFragmentClientRgContainer)
    RadioGroup confirmOrderDetailsFragmentClientRgContainer;
    @BindView(R.id.confirmOrderDetailsFragmentClientTvTotal)
    TextView confirmOrderDetailsFragmentClientTvTotal;
    @BindView(R.id.confirmOrderDetailsFragmentClientTvDeliveryCost)
    TextView confirmOrderDetailsFragmentClientTvDeliveryCost;
    @BindView(R.id.confirmOrderDetailsFragmentClientTvTotalAmount)
    TextView confirmOrderDetailsFragmentClientTvTotalAmount;
    @BindView(R.id.Button_Confirm_Order)
    Button ButtonConfirmOrder;
    Unbinder unbinder;
    @BindView(R.id.Rv2)
    LinearLayout Rv2;
    @BindView(R.id.cardView3)
    CardView cardView3;
    private ApiServicesSale apiServices;
    private int idRestauratant;
    private String name, phone;
    private String total;
    private int choice = 1;
    List<Integer> items =new ArrayList<>();
    List<Integer> quantities=new ArrayList<>();
    List<String>  notes=new ArrayList<>();
    public ConfirmOrderDetailsClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_order_details_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getinformation();
        confirmOrderDetailsFragmentClientTvTotal.setText(total);
        return view;
    }

    private void getinformation() {
        apiServices.getinformationrestaurant(idRestauratant).enqueue(new Callback<InformationRestaurant>() {
            @Override
            public void onResponse(Call<InformationRestaurant> call, Response<InformationRestaurant> response) {
                if (response.body().getStatus() == 1) {
                    Data data = response.body().getData();
                    confirmOrderDetailsFragmentClientTvAddress.setText(data.getRegion().getCity().getName() + "_" +
                                    data.getRegion().getName());
                    confirmOrderDetailsFragmentClientTvDeliveryCost.setText(data.getMinimumCharger());
                    confirmOrderDetailsFragmentClientTvTotalAmount.setText((int) data.getDeliveryCost());

                    name = data.getName();
                    phone = data.getPhone();
                    total = data.getMinimumCharger() + data.getMinimumCharger();
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<InformationRestaurant> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.confirmOrderDetailsFragmentClientRbPayOnline, R.id.confirmOrderDetailsFragmentClientRbCash, R.id.confirmOrderDetailsFragmentClientRgContainer, R.id.Button_Confirm_Order})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.confirmOrderDetailsFragmentClientRgContainer:
                boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.confirmOrderDetailsFragmentClientRbPayOnline:
                    if (checked)
                        choice=1;
                        break;
                case R.id.confirmOrderDetailsFragmentClientRbCash:
                    if (checked)
                        choice = 2;


                    break;
            }
                break;
            case R.id.Button_Confirm_Order:
                confirm();
                break;
        }
    }

    private void confirm() {
apiServices.getnewOrder("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB",idRestauratant,confirmOrderDetailsFragmentClientEtNote.getText().toString(),
        confirmOrderDetailsFragmentClientTvAddress.getText().toString(),choice,phone,name,items,quantities,notes).enqueue(new Callback<NewOrder>() {
    @Override
    public void onResponse(Call<NewOrder> call, Response<NewOrder> response) {
        if (response.body().getStatus()==1) {
            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<NewOrder> call, Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

    }
});


    }


}

