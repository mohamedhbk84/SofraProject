package com.example.sofra.ui.Fragment.SaleFragment.UserCycle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.categories.Categories;
import com.example.sofra.data.model.cities.Cities;
import com.example.sofra.data.model.generalData.GeneratedSpinner;
import com.example.sofra.data.model.regions.Regions;
import com.example.sofra.data.model.restaurantRegister.RestaurantRegister;
import com.example.sofra.data.model.updateProfileClient.DataProfile;
import com.example.sofra.data.model.updateProfileClient.ProfileClient;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.helper.MultiSelectionSpinner;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofra.helper.HelperMethod.convertToRequestBody;
import static com.example.sofra.helper.HelperMethod.openAlbum;

/**
 * A simple {@link Fragment} subclass.
 */
public class User_Register_Fragment extends Fragment {
//    MultiSelectionSpinner FragmentSellerRegisterCategory;
//    ArrayList<GeneratedSpinner> ArrayListCity;
//    ArrayList<GeneratedSpinner> ArrayListRegions;
//    @BindView(R.id.Fragment_seller_register_img_profile)
//    CircleImageView FragmentSellerRegisterImgProfile;
//    @BindView(R.id.Fragment_seller_register_ed_restaurantname)
//    TextInputLayout FragmentSellerRegisterEdRestaurantname;
//    @BindView(R.id.Fragment_seller_register_email)
//    TextInputLayout FragmentSellerRegisterEmail;
//    @BindView(R.id.Fragment_seller_register_phone)
//    TextInputLayout FragmentSellerRegisterPhone;
//    @BindView(R.id.Fragment_seller_register_sp_city)
//    Spinner FragmentSellerRegisterSpCity;
//    @BindView(R.id.relative)
//    RelativeLayout relative;
//    @BindView(R.id.Fragment_seller_register_sp_block)
//    Spinner FragmentSellerRegisterSpBlock;
//    @BindView(R.id.relative2)
//    RelativeLayout relative2;
//    @BindView(R.id.Fragment_seller_register_ed_password)
//    TextInputLayout FragmentSellerRegisterEdPassword;
//    @BindView(R.id.Fragment_seller_register_ed_password_confirm)
//    TextInputLayout FragmentSellerRegisterEdPasswordConfirm;
//    @BindView(R.id.Fragment_seller_register_btn_continue)
//    Button FragmentSellerRegisterBtnContinue;
//    @BindView(R.id.Fragment_sale_User_Register)
//    Button FragmentSaleUserRegister;
    Unbinder unbinder;
//    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();
//    private ApiServicesSale apiServices;
//    List<String> citiesnames = new ArrayList<>();
//    List<Integer> citiesids = new ArrayList<>();
//
//
//    List<String> categorynames = new ArrayList<>();
//    List<Integer> categoryids = new ArrayList<>();
//    private int Region_id = 0;


    public User_Register_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_register, container, false);
        unbinder = ButterKnife.bind(this, view);

//        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
//
//        getcity();

        return view;
    }

//    private void getCategory() {
//        apiServices.getCategories().enqueue(new Callback<Categories>() {
//            @Override
//            public void onResponse(Call<Categories> call, Response<Categories> response) {
//                if (response.body().getStatus() == 1) {
//                    for (int i = 0; i < response.body().getData().size(); i++) {
//
//                        categorynames.add(response.body().getData().get(i).getName());
//                        categoryids.add(response.body().getData().get(i).getId());
//                        FragmentSellerRegisterCategory.setItems(categorynames, categoryids);
//                    }
//                } else {
//                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Categories> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//
//
//    private void getcity() {
//        {
//            apiServices.getcity().enqueue(new Callback<Cities>() {
//                @Override
//                public void onResponse(Call<Cities> call, Response<Cities> response) {
//
//                    if (response.body().getStatus() == 1) {
//
//                        citiesnames.add("المدينة");
//                        citiesids.add(0);
//                        for (int i = 0; i < response.body().getData().getData().size(); i++) {
//
//                            citiesnames.add(response.body().getData().getData().get(i).getName());
//                            citiesids.add(response.body().getData().getData().get(i).getId());
//                        }
//
//
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, citiesnames);
//
//                        FragmentSellerRegisterSpCity.setAdapter(adapter);
//                        FragmentSellerRegisterSpCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                if (position == 0) {
//
//                                } else {
//                                    getRegion(citiesids.get(position));
//                                }
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
//
//                    }
//                }
//
//
//                @Override
//                public void onFailure(Call<Cities> call, Throwable t) {
//
//                }
//            });
//        }
//
//    }
//
//    private void getRegion(int id) {
//        apiServices.getRegions(id).enqueue(new Callback<Regions>() {
//            @Override
//            public void onResponse(Call<Regions> call, Response<Regions> response) {
//                if (response.body().getStatus() == 1) {
//
//                    categorynames.add("الحي");
//                    categoryids.add(0);
//                    for (int i = 0; i < response.body().getDataPagination().getData().size(); i++) {
//
//
//                        categorynames.add(response.body().getDataPagination().getData().get(i).getName());
//                        categoryids.add(response.body().getDataPagination().getData().get(i).getId());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categorynames);
//                    FragmentSellerRegisterSpBlock.setAdapter(adapter);
//                    FragmentSellerRegisterSpBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            if (position == 0) {
//
//                            } else {
//                                Region_id = categoryids.get(position);
//                            }
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Regions> call, Throwable t) {
//
//            }
//        });
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


//    private void addImage() {
//        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
//            @Override
//            public void onAction(@NonNull ArrayList<AlbumFile> result) {
//                // TODO accept the result.
//                result.get(0).getPath();
//                ImagesFiles.addAll(result);
//                convertFileToMultipart(result.get(0).getPath(), "photo");
//            }
//        };
//        openAlbum(1, getActivity(), ImagesFiles, action);
//    }
//
//
//    @OnClick({R.id.Fragment_seller_register_img_profile, R.id.Fragment_seller_register_btn_continue})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.Fragment_seller_register_img_profile:
//                addImage();
//                break;
//            case R.id.Fragment_seller_register_btn_continue:
//                getButterKife();
//                getCategory();
//                User_Continue_Regiseter_Fragment user_continue_regiseter_fragment = new User_Continue_Regiseter_Fragment();
//                HelperMethod.replace(user_continue_regiseter_fragment, getActivity().getSupportFragmentManager(), R.id.User_Home_Cycle_Frame, null, null);
//                break;
//        }
//    }

//    private void getButterKife() {
//        FragmentSaleUserRegister1 = findViewById(R.id.Fragment_sale_User_Register);
//        FragmentSaleUserCaheMoney = findViewById(R.id.Fragment_sale_User_Cahe_money);
//        FragmentSaleUserDeliveryMoney = findViewById(R.id.Fragment_sale_User_delivery_money);
//        FragmentSaleWhatsup = findViewById(R.id.Fragment_sale_Whatsup);
//



//        FragmentSaleUserRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                apiServices.getRestaurantRegister(
//                        convertToRequestBody(FragmentSellerRegisterEdRestaurantname.getEditText().getText().toString())
//                        , convertToRequestBody(FragmentSellerRegisterEmail.getEditText().getText().toString())
//                        , convertToRequestBody(FragmentSellerRegisterEdPassword.getEditText().getText().toString()),
//                        convertToRequestBody(FragmentSellerRegisterEdPasswordConfirm.getEditText().getText().toString())
//                        , convertToRequestBody(FragmentSellerRegisterPhone.getEditText().getText().toString())
//                        , convertToRequestBody(FragmentSaleWhatsup.getText().toString())
//                        , convertToRequestBody(String.valueOf(Region_id)), FragmentSellerRegisterCategory.getSelectedId()
//                        , convertToRequestBody(FragmentSaleUserCaheMoney.getText().toString())
//                        , convertToRequestBody(FragmentSaleUserDeliveryMoney.getText().toString()),
//                        convertFileToMultipart(ImagesFiles.get(0).getPath(), "photo"))
//                        .enqueue(new Callback<RestaurantRegister>() {
//                            @Override
//                            public void onResponse(Call<RestaurantRegister> call, Response<RestaurantRegister> response) {
//
//
//
//                                Log.d("response", response.body().getMsg());
//
//                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                                if (response.body().getStatus() == 1) {
//
//
//
//                                    Intent intent = new Intent(RegisterRestaurantActivity.this, MainHomeActivity.class);
//                                    startActivity(intent);
//
//                                } else {
//
//                                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<RestaurantRegister> call, Throwable t) {
//                                Log.d("onFailure", t.getMessage());
//
//                            }
//                        });
//
//            }
//        });




//    }
//
//
}
//



