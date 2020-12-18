package com.example.sofra.ui.Activity.SaleActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sofra.MainActivity;
import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.categories.Categories;
import com.example.sofra.data.model.cities.Cities;
import com.example.sofra.data.model.regions.Regions;
import com.example.sofra.data.model.restaurantRegister.RestaurantRegister;
import com.example.sofra.helper.MultiSelectionSpinner;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofra.helper.HelperMethod.convertToRequestBody;
import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.sofra.helper.HelperMethod.openAlbum;

public class RegisterRestaurantActivity extends Activity {

    MultiSelectionSpinner FragmentSellerRegisterCategory1;


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
    @BindView(R.id.Fragment_seller_register_ed_password_confirm)
    TextInputLayout FragmentSellerRegisterEdPasswordConfirm;
    @BindView(R.id.Fragment_seller_register_btn_continue)
    Button FragmentSellerRegisterBtnContinue;
    @BindView(R.id.Fragment_seller_register_img_profile)
    PorterShapeImageView FragmentSellerRegisterImgProfile;


    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();
    private ApiServicesSale apiServices;
    List<String> citiesnames = new ArrayList<>();
    List<Integer> citiesids = new ArrayList<>();
    List<String> Category = new ArrayList<>();
    List<Integer> CategoryId = new ArrayList<>();

    List<String> categorynames = new ArrayList<>();
    List<Integer> categoryids = new ArrayList<>();
    private int Region_id = 0;
    Button FragmentSaleUserRegister, registerRestaurantActivityBtnContinues;
    EditText FragmentSaleUserCaheMoney, FragmentSaleUserDeliveryMoney, FragmentSaleWhatsup;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
        ButterKnife.bind(this);

        FragmentSellerRegisterImgProfile = findViewById(R.id.Fragment_seller_register_img_profile);
        registerRestaurantActivityBtnContinues = findViewById(R.id.Fragment_seller_register_btn_continue);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);

        getcity();
        onclick();


    }

    private void onclick() {

        registerRestaurantActivityBtnContinues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.fragment_user_register);
                getButKife();
                getCategory();

            }
        });
        FragmentSellerRegisterImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
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
                        for (int i = 0; i < response.body().getData().getData().size(); i++) {

                            citiesnames.add(response.body().getData().getData().get(i).getName());
                            citiesids.add(response.body().getData().getData().get(i).getId());
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterRestaurantActivity.this, android.R.layout.simple_spinner_item, citiesnames);

                        FragmentSellerRegisterSpCity.setAdapter(adapter);
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterRestaurantActivity.this, android.R.layout.simple_spinner_item, categorynames);
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


    private void addImage() {
        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                // TODO accept the result.
                result.get(0).getPath();
                ImagesFiles.addAll(result);
                onLoadImageFromUrl(FragmentSellerRegisterImgProfile, ImagesFiles.get(0).getPath(), RegisterRestaurantActivity.this);
                convertFileToMultipart(result.get(0).getPath(), "photo");
            }
        };
        openAlbum(1, RegisterRestaurantActivity.this, ImagesFiles, action);
    }

//    @OnClick({R.id.Fragment_seller_register_img_profile, R.id.Fragment_seller_register_btn_continue})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.Fragment_seller_register_img_profile:
//
//                break;
//            case R.id.Fragment_seller_register_btn_continue:
//                getButterKife();
//                getCategory();
//                break;
//        }
//    }

    private void getCategory() {
        apiServices.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.body().getStatus() == 1) {

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        CategoryId.add(response.body().getData().get(i).getId());
                        Category.add(response.body().getData().get(i).getName());

                        FragmentSellerRegisterCategory1.setItems(Category, CategoryId);
                    }

                } else {
                    Toast.makeText(RegisterRestaurantActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Toast.makeText(RegisterRestaurantActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void getButKife() {

        FragmentSaleUserRegister = findViewById(R.id.Fragment_sale_User_Register);
        FragmentSaleUserCaheMoney = findViewById(R.id.Fragment_sale_User_Cahe_money1);
        FragmentSaleUserDeliveryMoney = findViewById(R.id.Fragment_sale_User_delivery_money);
        FragmentSaleWhatsup = findViewById(R.id.Fragment_sale_Whatsup);
        FragmentSellerRegisterCategory1 = findViewById(R.id.FragmentSellerRegisterCategory);

        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);

        FragmentSaleUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 apiServices.getRestaurantRegister(
                            convertToRequestBody(FragmentSellerRegisterEdRestaurantname.getEditText().getText().toString())
                            , convertToRequestBody(FragmentSellerRegisterEmail.getEditText().getText().toString())
                            , convertToRequestBody(FragmentSellerRegisterEdPassword.getEditText().getText().toString()),
                            convertToRequestBody(FragmentSellerRegisterEdPasswordConfirm.getEditText().getText().toString())
                            , convertToRequestBody(FragmentSellerRegisterPhone.getEditText().getText().toString())
                            , convertToRequestBody(FragmentSaleWhatsup.getText().toString())
                            , convertToRequestBody(String.valueOf(Region_id)), FragmentSellerRegisterCategory1.getSelectedId()
                            , convertToRequestBody(FragmentSaleUserCaheMoney.getText().toString())
                            , convertToRequestBody(FragmentSaleUserDeliveryMoney.getText().toString()),
                            convertFileToMultipart(ImagesFiles.get(0).getPath(), "photo"))

                         .enqueue(new Callback<RestaurantRegister>() {
                    @Override
                    public void onResponse(Call<RestaurantRegister> call, Response<RestaurantRegister> response) {



                        if (response.body().getStatus() == 1) {


                            Intent intent = new Intent(RegisterRestaurantActivity.this, MainActivity.class);
                            startActivity(intent);
                            Log.d("response", response.body().getMsg());

                            Toast.makeText(RegisterRestaurantActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(RegisterRestaurantActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantRegister> call, Throwable t) {
                        Log.d("onFailure", t.getMessage());

                    }
                });


            }
        });


    }
}
