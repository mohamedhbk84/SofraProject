package com.example.sofra.ui.Fragment.SaleFragment.UserCycle;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.resetPassword.ResetPassword;
import com.example.sofra.helper.HelperMethod;

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
public class User_Forget_Password_Fragment extends Fragment {


    @BindView(R.id.Logo)
    ImageView Logo;
    @BindView(R.id.Text)
    TextView Text;
    @BindView(R.id.Forget_Password_Fragment_email)
    TextInputLayout ForgetPasswordFragmentEmail;
    @BindView(R.id.Forget_Password_Fragment_Btn_Send)
    Button ForgetPasswordFragmentBtnSend;
    Unbinder unbinder;
    private ApiServicesSale apiServices;

    public User_Forget_Password_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user__forget__password_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        forgetpassword();
        return view;
    }

    private void forgetpassword() {
        ForgetPasswordFragmentBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ResetPassword> call = apiServices.resetPassword(ForgetPasswordFragmentEmail.getEditText().getText().toString());
                call.enqueue(new Callback<ResetPassword>() {
                    @Override
                    public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {

                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        if (response.body().getStatus() == 1) {
                         User_Forget_Password_Step2_Fragment user_forget_password_step2_fragment = new User_Forget_Password_Step2_Fragment();
                            HelperMethod.replace(user_forget_password_step2_fragment, getActivity().getSupportFragmentManager(), R.id.User_Home_Cycle_Frame, null, null);


                        }
                    }

                    @Override
                    public void onFailure(Call<ResetPassword> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
