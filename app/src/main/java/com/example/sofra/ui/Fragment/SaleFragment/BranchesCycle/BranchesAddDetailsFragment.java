package com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.restaurant_new_item.RestaurantNewItem;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.Detais_Order_SaleFragment;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.data.local.SharedPreferenceManager.USER_API_TOKEN;
import static com.example.sofra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofra.helper.HelperMethod.convertToRequestBody;
import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.sofra.helper.HelperMethod.openAlbum;

//import com.example.sofra.data.model.restaurant_my_items.RestaurantMyItems;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchesAddDetailsFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.getBranche_Details_Image)
    PorterShapeImageView getBrancheDetailsImage;
    @BindView(R.id.getBranche_Details_getName)
    EditText getBrancheDetailsGetName;
    @BindView(R.id.Branche_Details_getName1)
    CardView BrancheDetailsGetName1;
    @BindView(R.id.getBranche_Details_getWasfa)
    EditText getBrancheDetailsGetWasfa;
    @BindView(R.id.Branche_Details_getWasfa1)
    CardView BrancheDetailsGetWasfa1;
    @BindView(R.id.getBranche_Details_money)
    EditText getBrancheDetailsMoney;
    @BindView(R.id.Branche_Details_money1)
    CardView BrancheDetailsMoney1;
    @BindView(R.id.getBranche_Details_moneyin_Offer)
    EditText getBrancheDetailsMoneyinOffer;

    @BindView(R.id.getBranche_Details_Time_to_Offer)
    EditText getBrancheDetailsTimeToOffer;

    @BindView(R.id.getBranche_Details_Btn_Add)
    Button getBrancheDetailsBtnAdd;
    private ApiServicesSale apiServices;
    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();

    public BranchesAddDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branches_add_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        return view;
    }

    private void addImage() {
        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                // TODO accept the result.
                ImagesFiles.clear();
                ImagesFiles.addAll(result);
                onLoadImageFromUrl(getBrancheDetailsImage, ImagesFiles.get(0).getPath(), getActivity());
            }
        };
        openAlbum(1, getActivity(), ImagesFiles, action);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void add() {

        String description = getBrancheDetailsGetWasfa.getText().toString();
        final String name = getBrancheDetailsGetName.getText().toString();
        String price = getBrancheDetailsMoney.getText().toString();
        String preparing_time = getBrancheDetailsTimeToOffer.getText().toString();
        String api_token = SharedPreferenceManager.LoadData(getActivity(), USER_API_TOKEN);

        apiServices.AddProduct(convertToRequestBody(description),
                convertToRequestBody(price),
                convertToRequestBody(preparing_time),
                convertToRequestBody(name),
                convertToRequestBody("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx"),
                convertFileToMultipart(ImagesFiles.get(0).getPath()
                        , "photo")).enqueue(new Callback<RestaurantNewItem>() {
            @Override
            public void onResponse(Call<RestaurantNewItem> call, Response<RestaurantNewItem> response) {
                if (response.body().getStatus() == 1) {
                    try {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Detais_Order_SaleFragment detais_order_saleFragment = new Detais_Order_SaleFragment();
                        HelperMethod.replace(detais_order_saleFragment, getActivity().getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantNewItem> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.getBranche_Details_Image, R.id.getBranche_Details_Btn_Add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getBranche_Details_Image:
                addImage();
                break;
            case R.id.getBranche_Details_Btn_Add:
                add();
                break;
        }
    }
}
