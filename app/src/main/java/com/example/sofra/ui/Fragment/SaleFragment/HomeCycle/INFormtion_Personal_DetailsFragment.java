package com.example.sofra.ui.Fragment.SaleFragment.HomeCycle;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
//import com.example.sofra.data.model.governrates.Governorate;
//import com.example.sofra.data.model.governrates.GovernorateData;
import com.example.sofra.data.model.cities.Cities;
import com.example.sofra.data.model.regions.Regions;
import com.example.sofra.data.model.restaurantprofile.Data;
import com.example.sofra.data.model.restaurantprofile.RestaurantProfile;
import com.example.sofra.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.example.sofra.data.local.SharedPreferenceManager.User_password;

/**
 * A simple {@link Fragment} subclass.
 */
public class INFormtion_Personal_DetailsFragment extends Fragment {


    @BindView(R.id.Fragment_seller_register_img_profile)
    CircleImageView FragmentSellerRegisterImgProfile;
    @BindView(R.id.Fragment_seller_register_ed_restaurantname)
    TextInputLayout FragmentSellerRegisterEdRestaurantname;
    @BindView(R.id.Fragment_seller_register_email)
    TextInputLayout FragmentSellerRegisterEmail;
    @BindView(R.id.Fragment_seller_register_phone)
    TextInputLayout FragmentSellerRegisterPhone;
    @BindView(R.id.Fragment_seller_register_sp_city)
    Spinner FragmentSellerRegisterSpCity;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.Fragment_seller_register_sp_block)
    Spinner FragmentSellerRegisterSpBlock;
    @BindView(R.id.relative2)
    RelativeLayout relative2;
    @BindView(R.id.Fragment_seller_register_ed_password)
    TextInputLayout FragmentSellerRegisterEdPassword;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private String api_token;


;
    List<String> citiesnames = new ArrayList<>();
    List<Integer> citiesids = new ArrayList<>();


    List<String> categorynames = new ArrayList<>();
    List<Integer> categoryids = new ArrayList<>();
    private int Region_id;
    private Integer id;

    public INFormtion_Personal_DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informtion__personal__details, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        viewData();
        getcity();
        return view;
    }



    private void viewData() {
        apiServices.getProfile("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx").enqueue(new Callback<RestaurantProfile>() {
            @Override
            public void onResponse(Call<RestaurantProfile> call, Response<RestaurantProfile> response) {
                if (response.body().getStatus() == 1) {
                    try {

                         Data data = response.body().getData();
                        HelperMethod.onLoadImageFromUrl(FragmentSellerRegisterImgProfile, data.getUser().getPhotoUrl(), getContext());
                        FragmentSellerRegisterEdRestaurantname.getEditText().setText(data.getUser().getName());
                        FragmentSellerRegisterEmail.getEditText().setText(data.getUser().getEmail());
                        FragmentSellerRegisterPhone.getEditText().setText(data.getUser().getPhone());
                        FragmentSellerRegisterEdPassword.getEditText().setText(User_password);


//                        String gov_name = (String) SharedPreferenceManager.LoadData(getActivity(), );

//                        String city_name = SharedPreferenceManager.LoadData(getActivity(), gov_name);
//                        FragmentEditprofileSpGov.setSelection((response.body().getData().getClient().getCity().getGovernorate().getId()));
//                        FragmentEditprofileSpCity.setSelection(response.body().getData().getClient().getCity().getId());
//                        String password = SharedPreferenceManager.LoadData(getActivity(), PASSWORD);


                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantProfile> call, Throwable t) {

            }
        });
    }
    private void getcity() {
        {
            apiServices.getcity().enqueue(new Callback<Cities>() {
                @Override
                public void onResponse(Call<Cities> call, Response<Cities> response) {

                    if (response.body().getStatus() == 1) {

                        citiesnames.add("المدينة");
                        citiesids.add(0);
                        int pos = 0;
                        for (int i = 0; i < response.body().getData().getData().size(); i++) {

                            citiesnames.add(response.body().getData().getData().get(i).getName());
                            citiesids.add(response.body().getData().getData().get(i).getId());

                            if (response.body().getData().getData().get(i).getId().equals(id)) {
                                pos = 1 + i;
                            }
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, citiesnames);

                        FragmentSellerRegisterSpCity.setAdapter(adapter);
                        FragmentSellerRegisterSpCity.setSelection(pos);
                        FragmentSellerRegisterSpCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position == 0) {

                                } else {
                                    getRegion(citiesids.get(position));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }


                @Override
                public void onFailure(Call<Cities> call, Throwable t) {

                }
            });
        }

    }

    private void getRegion(int id) {
        apiServices.getRegions(id).enqueue(new Callback<Regions>() {
            @Override
            public void onResponse(Call<Regions> call, Response<Regions> response) {
                if (response.body().getStatus() == 1) {

                    categorynames.add("الحي");
                    categoryids.add(0);
                    for (int i = 0; i < response.body().getDataPagination().getData().size(); i++) {


                        categorynames.add(response.body().getDataPagination().getData().get(i).getName());
                        categoryids.add(response.body().getDataPagination().getData().get(i).getId());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categorynames);
                    FragmentSellerRegisterSpBlock.setAdapter(adapter);
                    FragmentSellerRegisterSpBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {

                            } else {
                                Region_id = categoryids.get(position);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Regions> call, Throwable t) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
