package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.addContact.AddContact;
import com.example.sofra.data.model.addContact.Data;

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
public class ContactMeRestaurantFragment extends Fragment {


    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.contactMeRestaurantFragmentEditName)
    EditText contactMeRestaurantFragmentEditName;
    @BindView(R.id.contactMeRestaurantEmail)
    EditText contactMeRestaurantEmail;
    @BindView(R.id.contactMeRestaurantPhone)
    EditText contactMeRestaurantPhone;
    @BindView(R.id.contactMeRestaurantFragmentEditMassage)
    EditText contactMeRestaurantFragmentEditMassage;
    @BindView(R.id.contactMeRestaurantRbComplaint)
    RadioButton contactMeRestaurantRbComplaint;
    @BindView(R.id.contactMeRestaurantRbSuggestion)
    RadioButton contactMeRestaurantRbSuggestion;
    @BindView(R.id.contactMeRestaurantRbEnquiry)
    RadioButton contactMeRestaurantRbEnquiry;
    @BindView(R.id.contactMeRestaurantFragmentRGContent)
    RadioGroup contactMeRestaurantFragmentRGContent;
    @BindView(R.id.contactMeRestaurantFragmentBtnSend)
    Button contactMeRestaurantFragmentBtnSend;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private String type = "شكوي";

    public ContactMeRestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_me_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.contactMeRestaurantRbComplaint, R.id.contactMeRestaurantRbSuggestion, R.id.contactMeRestaurantRbEnquiry, R.id.contactMeRestaurantFragmentRGContent, R.id.contactMeRestaurantFragmentBtnSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contactMeRestaurantRbSuggestion:
                type = "اقتراح";
                Toast.makeText(getActivity(),type, Toast.LENGTH_LONG).show();
                break;
            case R.id.contactMeRestaurantRbEnquiry:
                type = "شكوي";
                Toast.makeText(getActivity(),type, Toast.LENGTH_LONG).show();
                break;
            case R.id.contactMeRestaurantFragmentRGContent:
                type = "استعلامات";
                Toast.makeText(getActivity(),type, Toast.LENGTH_LONG).show();
                break;
            case R.id.contactMeRestaurantFragmentBtnSend:
                addContact();
                break;
        }
    }

    private void addContact() {
        apiServices.addContact(contactMeRestaurantFragmentEditName.getText().toString(),
                contactMeRestaurantEmail.getText().toString(),
                contactMeRestaurantPhone.getText().toString(),
                type,contactMeRestaurantFragmentEditMassage.getText().toString())
                .enqueue(new Callback<AddContact>() {
            @Override
            public void onResponse(Call<AddContact> call, Response<AddContact> response) {
                if (response.body().getStatus()==1) {

                    Toast.makeText(getActivity(), "mmmmmmm", Toast.LENGTH_SHORT).show();
                    clear();
                    Toast.makeText(getActivity(), "send", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddContact> call, Throwable t) {
                Toast.makeText(getActivity(),"mmm", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void clear() {
        contactMeRestaurantFragmentEditName.getText().clear();
        contactMeRestaurantEmail.getText().clear();
        contactMeRestaurantPhone.getText().clear();
        contactMeRestaurantFragmentEditMassage.getText().clear();

    }
}
