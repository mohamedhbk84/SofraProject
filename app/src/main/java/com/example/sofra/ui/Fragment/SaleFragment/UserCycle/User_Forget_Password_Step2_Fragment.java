package com.example.sofra.ui.Fragment.SaleFragment.UserCycle;


import android.os.Bundle;
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
import com.example.sofra.data.model.newPassword.NewPassword;

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
public class User_Forget_Password_Step2_Fragment extends Fragment {


    @BindView(R.id.Logo)
    ImageView Logo;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.Forget_Password_Fragment_Validation_code)
    EditText ForgetPasswordFragmentValidationCode;
    @BindView(R.id.Forget_Password_Fragment_New_Password)
    EditText ForgetPasswordFragmentNewPassword;
    @BindView(R.id.Forget_Password_Fragment_Confirm)
    EditText ForgetPasswordFragmentConfirm;
    @BindView(R.id.Forget_Password_Fragment_Btn_Change)
    Button ForgetPasswordFragmentBtnChange;
    Unbinder unbinder;
    private String pinCode;
    private String pass;
    private String cPass;
    private ApiServicesSale apiServices;

    public User_Forget_Password_Step2_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user__forget__password__step2_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Forget_Password_Fragment_Btn_Change)
    public void onViewClicked() {
        checkValidate();
    }

    private void checkValidate() {

    }{
        pinCode=ForgetPasswordFragmentValidationCode.getText().toString();
        pass =ForgetPasswordFragmentNewPassword.getText().toString();
        cPass=ForgetPasswordFragmentConfirm.getText().toString();
        if (TextUtils.isEmpty(pinCode)){
            ForgetPasswordFragmentValidationCode.setError("enter Pin Code");
        }else if(TextUtils.isEmpty(pass)){
            ForgetPasswordFragmentNewPassword.setError("enter password");
        }else if (TextUtils.isEmpty(cPass)){
            ForgetPasswordFragmentConfirm.setError("enter Confirm Password");
        }else {
            apiServices.newPassword(pinCode,pass,cPass).enqueue(new Callback<NewPassword>() {
                @Override
                public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<NewPassword> call, Throwable throwable) {
                    Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
