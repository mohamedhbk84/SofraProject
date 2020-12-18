package com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.restaurant_update_item.RestaurantUpdateItem;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.model.my_item.Datum;
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

import static com.example.sofra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sofra.helper.HelperMethod.convertToRequestBody;
import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.sofra.helper.HelperMethod.openAlbum;

/**
 * A simple {@link Fragment} subclass.
 */
public class Branches_getAllDetails_Fragment extends Fragment {


    public Datum RestaurantNewItem;
    @BindView(R.id.tthro)
    TextView tthro;
    @BindView(R.id.Branche_Details_Image)
    PorterShapeImageView BrancheDetailsImage;
    @BindView(R.id.Branche_Details_getName)
    EditText BrancheDetailsGetName;
    @BindView(R.id.Branche_Details_getWasfa)
    EditText BrancheDetailsGetWasfa;
    @BindView(R.id.Branche_Details_money)
    EditText BrancheDetailsMoney;
    @BindView(R.id.Branche_Details_Time_to_Offer)
    EditText BrancheDetailsTimeToOffer;
    Unbinder unbinder;
    @BindView(R.id.getBranche_Details_Btn_edit)
    Button getBrancheDetailsBtnEdit;
    private ApiServicesSale apiServices;
    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();

    public Branches_getAllDetails_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branches_get_all_details_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        BrancheDetailsGetWasfa.setText(RestaurantNewItem.getDescription());
        BrancheDetailsGetName.setText(RestaurantNewItem.getName());
        BrancheDetailsMoney.setText(RestaurantNewItem.getPrice());
        BrancheDetailsTimeToOffer.setText(RestaurantNewItem.getPreparingTime());
        onLoadImageFromUrl(BrancheDetailsImage, RestaurantNewItem.getPhotoUrl(), getContext());

        return view;
    }

    private void addImage() {
        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                // TODO accept the result.
                ImagesFiles.clear();
                ImagesFiles.addAll(result);
                onLoadImageFromUrl(BrancheDetailsImage, ImagesFiles.get(0).getPath(), getActivity());
            }
        };
        openAlbum(1, getActivity(), ImagesFiles, action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Branche_Details_Image, R.id.getBranche_Details_Btn_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Branche_Details_Image:
                addImage();
                break;
            case R.id.getBranche_Details_Btn_edit:
                onClicked();
                break;
        }
    }

    public void onClicked() {


        String description = BrancheDetailsGetWasfa.getText().toString();
        if (description.isEmpty()) {
            BrancheDetailsGetWasfa.getHint().toString();
        }

        String name = BrancheDetailsGetName.getText().toString();
        if (name.isEmpty()) {
            BrancheDetailsGetName.getHint().toString();
        }

        String price = BrancheDetailsMoney.getText().toString();
        if (price.isEmpty()) {
            BrancheDetailsMoney.getHint().toString();
        }

        String preparing_time = BrancheDetailsTimeToOffer.getText().toString();
        if (preparing_time.isEmpty()) {
            BrancheDetailsTimeToOffer.getHint().toString();
        }

        Call<RestaurantUpdateItem> call;

        if (ImagesFiles.size() != 0) {
            call = apiServices.updateProduct(convertToRequestBody(description),
                    convertToRequestBody(price),
                    convertToRequestBody(preparing_time),
                    convertToRequestBody(String.valueOf(RestaurantNewItem.getId())),
                    convertToRequestBody(name),
                    convertToRequestBody("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx"),
                    convertFileToMultipart(ImagesFiles.get(0).getPath(), "photo")
            );
        } else {
            call = apiServices.updateProduct(convertToRequestBody(description),
                    convertToRequestBody(price),
                    convertToRequestBody(preparing_time),
                    convertToRequestBody(String.valueOf(RestaurantNewItem.getId())),
                    convertToRequestBody(name),
                    convertToRequestBody("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx"));
        }
        call.enqueue(new Callback<RestaurantUpdateItem>() {
            @Override
            public void onResponse(Call<RestaurantUpdateItem> call, Response<RestaurantUpdateItem> response) {
                if (response.body().getStatus() == 1) {
                    try {
                        Detais_Order_SaleFragment detais_order_saleFragment = new Detais_Order_SaleFragment();
                        HelperMethod.replace(detais_order_saleFragment, getActivity().getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<RestaurantUpdateItem> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
