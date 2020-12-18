package com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.offer.Offer;
import com.example.sofra.helper.HelperMethod;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.sofra.helper.HelperMethod.openAlbum;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewOFFerFragment extends Fragment {


    @BindView(R.id.th_txt)
    TextView thTxt;
    @BindView(R.id.Add_Image_Offer)
    ImageView AddImageOffer;
    @BindView(R.id.Add_Txt_NameOfOffer)
    EditText AddTxtNameOfOffer;
    @BindView(R.id.Add_Description)
    EditText AddDescription;
    @BindView(R.id.Add_Data_In)
    TextView AddDataIn;
    @BindView(R.id.Add_Data_Out)
    TextView AddDataOut;
    @BindView(R.id.Add_Btn)
    Button AddBtn;
    Unbinder unbinder;
    private Calendar mCalendar;
    private ApiServicesSale apiServices;
    private int offer = 21;

    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();

    public AddNewOFFerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_offer, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);


        showData();
        return view;
    }

    private void addImage() {
        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                // TODO accept the result.
                ImagesFiles.clear();
                ImagesFiles.addAll(result);
                onLoadImageFromUrl(AddImageOffer, ImagesFiles.get(0).getPath(), getActivity());
            }
        };
        openAlbum(1, getActivity(), ImagesFiles, action);
    }


    private void update() {

//        final String name = AddTxtNameOfOffer.getText().toString();
//        String description = AddDescription.getText().toString();
//        String api_token = SharedPreferenceManager.LoadData(getActivity(), USER_API_TOKEN);
        apiServices.getoffer(offer).enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                if (response.body().getStatus()==1) {
                    try {



                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Branches_New_Present_Fragment new_present_fragment = new Branches_New_Present_Fragment();
                        HelperMethod.replace(new_present_fragment,getActivity().getSupportFragmentManager(),R.id.Home_Sale_Frame_Container,null,null);
                    }catch (Exception e){
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Offer> call, Throwable t) {

            }
        });

    }

    private void showData() {
        mCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener last_donate_date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                getLastDonationDate();
            }

        };

        AddDataIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), last_donate_date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final DatePickerDialog.OnDateSetListener birth_date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                getBirthDate();
            }

        };


        AddDataOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), birth_date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void getLastDonationDate() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        AddDataIn.setText(sdf.format(mCalendar.getTime()));
    }


    private void getBirthDate() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        AddDataOut.setText(sdf.format(mCalendar.getTime()));


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Add_Btn)
    public void onViewClicked() {
    }

    @OnClick({R.id.Add_Image_Offer, R.id.Add_Btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Add_Image_Offer:
                addImage();
                break;
            case R.id.Add_Btn:
                update();
                break;
        }
    }
}
