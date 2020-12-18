package com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.cities.Cities;
import com.example.sofra.data.model.generalData.GeneratedSpinner;
import com.example.sofra.data.model.regions.Regions;
import com.example.sofra.data.model.updateProfileClient.DataProfile;
import com.example.sofra.data.model.updateProfileClient.ProfileClient;
import com.example.sofra.helper.HelperMethod;
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

import static com.example.sofra.data.local.SharedPreferenceManager.User_password;
import static com.example.sofra.helper.HelperMethod.checkCorrespondPassword;
import static com.example.sofra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofra.helper.HelperMethod.convertToRequestBody;
import static com.example.sofra.helper.HelperMethod.disappearKeypad;
import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.sofra.helper.HelperMethod.openAlbum;

/**
 * A simple {@link Fragment} subclass.
 */
public class Person_InformationAndEditFragment extends Fragment {

    ArrayList<GeneratedSpinner> ArrayListCity;
    GeneratedSpinner cityGeneratedModel;
    ArrayList<GeneratedSpinner> ArrayListRegions;
    GeneratedSpinner ArrayGeneratedRegions;
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

    @BindView(R.id.Fragment_seller_register_sp_block)
    Spinner FragmentSellerRegisterSpBlock;

    @BindView(R.id.Fragment_seller_register_btn_continue)
    Button FragmentSellerRegisterBtnContinue;
    Unbinder unbinder;
    @BindView(R.id.Edit_PasswordClient)
    EditText EditPasswordClient;
    @BindView(R.id.Edit_Confirm_Password_Client)
    EditText EditConfirmPasswordClient;
    private ApiServicesSale apiServices;
    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();
    List<String> citiesnames = new ArrayList<>();
    List<Integer> citiesids = new ArrayList<>();


    List<String> categorynames = new ArrayList<>();
    List<Integer> categoryids = new ArrayList<>();
    private int Region_id;
    private Integer id;


    public Person_InformationAndEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person__information_and_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getProfile();
        getcity();
        return view;
    }

    private void getProfile() {
        apiServices.getProfileClient("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB").enqueue(new Callback<ProfileClient>() {
            @Override
            public void onResponse(Call<ProfileClient> call, Response<ProfileClient> response) {
                if (response.body().getStatus() == 1) {
                    DataProfile data = response.body().getData();
                    onLoadImageFromUrl(FragmentSellerRegisterImgProfile, data.getUser().getPhotoUrl(), getContext());
                    FragmentSellerRegisterEdRestaurantname.getEditText().setText(data.getUser().getName());
                    FragmentSellerRegisterEmail.getEditText().setText(data.getUser().getEmail());
                    FragmentSellerRegisterPhone.getEditText().setText(data.getUser().getPhone());
                    EditPasswordClient.setText(User_password);
                    EditConfirmPasswordClient.setText(User_password);


//                    getcity(response.body().getData().getUser().getRegion().getCity().getId(),response.body().getData().getUser().getRegion().getId());

//                    for (int i = 0; i < citiesnames.size(); i++) {
//                        if (ArrayListCity.get(i).getId() == Integer.parseInt(response.body().getData().getUser().getRegion().getCityId())) {
//                            FragmentSellerRegisterSpCity.setSelection(i);
//                        }
//                    }
//                    for (int i = 0; i < ArrayListRegions.size(); i++) {
//                        if (ArrayListRegions.get(i).getId() == Integer.parseInt(response.body().getData().getUser().getRegionId())) {
//                            FragmentSellerRegisterSpBlock.setSelection(i);
//                        }
//                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProfileClient> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

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


    @OnClick({R.id.Fragment_seller_register_img_profile, R.id.Fragment_seller_register_btn_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Fragment_seller_register_img_profile:
                addImage();
                break;
            case R.id.Fragment_seller_register_btn_continue:
                if (checkCorrespondPassword(EditPasswordClient.getText().toString(), EditConfirmPasswordClient.getText().toString()))
                    ;

                showEdit();

                break;
        }
    }

    private void showEdit() {

        Call<ProfileClient> call;
        if (ImagesFiles.size() == 0) {
            call = apiServices.showEditProfileClient(convertToRequestBody("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB"),
                    convertToRequestBody(FragmentSellerRegisterEdRestaurantname.getEditText().getText().toString()),
                    convertToRequestBody(FragmentSellerRegisterEmail.getEditText().getText().toString()),
                    convertToRequestBody(FragmentSellerRegisterPhone.getEditText().getText().toString()),
                    convertToRequestBody(EditPasswordClient.getText().toString()),
                    convertToRequestBody(EditConfirmPasswordClient.getText().toString()),
                    convertToRequestBody("Qalubia"),
                    convertToRequestBody(String.valueOf(Region_id)),
                    convertFileToMultipart(ImagesFiles.get(0).getPath(), "photo"));
        } else {
            call = apiServices.showEditProfileClient(convertToRequestBody("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB"),
                    convertToRequestBody(FragmentSellerRegisterEdRestaurantname.getEditText().getText().toString()),
                    convertToRequestBody(FragmentSellerRegisterEmail.getEditText().getText().toString()),
                    convertToRequestBody(FragmentSellerRegisterPhone.getEditText().getText().toString()),
                    convertToRequestBody(EditPasswordClient.getText().toString()),
                    convertToRequestBody(EditConfirmPasswordClient.getText().toString()),
                    convertToRequestBody("Qalubia"),
                    convertToRequestBody(String.valueOf(Region_id)));
        }


        call.enqueue(new Callback<ProfileClient>() {
            @Override
            public void onResponse(Call<ProfileClient> call, Response<ProfileClient> response) {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileClient> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addImage() {
        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                // TODO accept the result.
                ImagesFiles.clear();
                ImagesFiles.addAll(result);
                onLoadImageFromUrl(FragmentSellerRegisterImgProfile, ImagesFiles.get(0).getPath(), getActivity());
            }
        };
        openAlbum(1, getActivity(), ImagesFiles, action);

    }
}
