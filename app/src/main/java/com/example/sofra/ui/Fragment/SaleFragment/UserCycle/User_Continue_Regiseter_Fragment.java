package com.example.sofra.ui.Fragment.SaleFragment.UserCycle;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.categories.Categories;
import com.example.sofra.data.model.restaurantRegister.RestaurantRegister;
import com.example.sofra.helper.MultiSelectionSpinner;
import com.example.sofra.ui.Activity.SaleActivity.RegisterRestaurantActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofra.helper.HelperMethod.convertToRequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class User_Continue_Regiseter_Fragment extends Fragment {


    @BindView(R.id.Fragment_seller_register_Categotry)
    MultiSelectionSpinner FragmentSellerRegisterCategory;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.Fragment_sale_User_Cahe_money)
    TextInputLayout FragmentSaleUserCaheMoney;
    @BindView(R.id.Fragment_sale_User_delivery_money)
    TextInputLayout FragmentSaleUserDeliveryMoney;
    @BindView(R.id.Txt_thre)
    TextView TxtThre;
    @BindView(R.id.Fragment_sale_User_number_of_phone)
    TextInputLayout FragmentSaleUserNumberOfPhone;
    @BindView(R.id.Fragment_sale_Whatsup)
    TextInputLayout FragmentSaleWhatsup;
    @BindView(R.id.id_thro)
    TextView idThro;
    @BindView(R.id.Fragment_sale_User_Image_Matgar)
    ImageView FragmentSaleUserImageMatgar;
    @BindView(R.id.Fragment_sale_User_Register)
    Button FragmentSaleUserRegister;
    Unbinder unbinder;
    @BindView(R.id.registerMoreRestaurantSwitchState)
    Switch registerMoreRestaurantSwitchState;
    private ApiServicesSale apiServices;

    List<String> categorynames = new ArrayList<>();
    List<Integer> categoryids = new ArrayList<>();
    private String KeyName, KeyEmail, KeyPhone, KeyPassword, KeyPasswordEmphasise;
    private String getAvailability, getDeliveryCost, getMinimumCharger, getWhatsapp;
    private int Regionsid;
    private String photo;

    public User_Continue_Regiseter_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user__continue__regiseter_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        start();
        getCategory();
        return view;
    }

    private void start() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            KeyName = bundle.getString("Name");
            KeyEmail = bundle.getString("Email");
            KeyPhone = bundle.getString("Phone");
            Regionsid = bundle.getInt("idRegions");
            KeyPassword = bundle.getString("Password");
            KeyPasswordEmphasise = bundle.getString("PasswordEmphasis");
            photo = bundle.getString("photo");
            getAvailability = bundle.getString("getAvailability");
            getMinimumCharger = bundle.getString("getMinimumCharger");
            getDeliveryCost = bundle.getString("getDeliveryCost");
            getWhatsapp = bundle.getString("getWhatsapp");

            FragmentSaleUserCaheMoney.getEditText().setText(getMinimumCharger);
            FragmentSaleUserDeliveryMoney.getEditText().setText(getDeliveryCost);
            FragmentSaleWhatsup.getEditText().setText(getWhatsapp);
            if (getAvailability.equals("open")) {

                registerMoreRestaurantSwitchState.setChecked(true);
            } else {
                registerMoreRestaurantSwitchState.setChecked(true);

            }
        }

        registerMoreRestaurantSwitchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getAvailability = "open";
                } else {
                    getAvailability = "Close";
                }
            }
        });

    }

    private void getCategory() {
        apiServices.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.body().getStatus() == 1) {
                    for (int i = 0; i < response.body().getData().size(); i++) {

                        categorynames.add(response.body().getData().get(i).getName());
                        categoryids.add(response.body().getData().get(i).getId());
                        FragmentSellerRegisterCategory.setItems(categorynames, categoryids);
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Fragment_sale_User_Register)
    public void onViewClicked() {
//        Intent i = new Intent(getActivity().getApplication(),Home_Sale_Cycle_Activty.class);
//        startActivity(i);

        getapi();
    }

    private void getapi() {
apiServices.getEditRestaurantRegister(
        convertToRequestBody(KeyName)
        , convertToRequestBody(KeyEmail)
        , convertToRequestBody(KeyPassword)
        , convertToRequestBody(KeyPasswordEmphasise)
        , convertToRequestBody(KeyPhone)
        , convertToRequestBody(FragmentSaleWhatsup.getEditText().getText().toString())
        , convertToRequestBody(String.valueOf(Regionsid)), FragmentSellerRegisterCategory.getSelectedId()
        , convertToRequestBody(FragmentSaleUserCaheMoney.getEditText().getText().toString())
        , convertToRequestBody(FragmentSaleUserDeliveryMoney.getEditText().getText().toString())
        , convertFileToMultipart(photo, "photo")
        , convertToRequestBody("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx")
        , convertToRequestBody(getAvailability))
        .enqueue(new Callback<RestaurantRegister>() {
            @Override
            public void onResponse(Call<RestaurantRegister> call, Response<RestaurantRegister> response) {
                if (response.body().getStatus()==1) {
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RestaurantRegister> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
