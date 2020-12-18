package com.example.sofra.ui.Fragment.SaleFragment.UserCycle;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.local.SharedPreferenceManager;
import com.example.sofra.data.model.notifyToken.NotifyToken;
import com.example.sofra.data.model.restaurant_login.RestaurantLogin;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;
import com.example.sofra.ui.Activity.SaleActivity.RegisterRestaurantActivity;

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
public class User_Login_Fragment extends Fragment {


    @BindView(R.id.text_thro)
    TextView textThro;
    @BindView(R.id.User_Login_Email)
    EditText UserLoginEmail;
    @BindView(R.id.User_Login_Password)
    TextInputLayout UserLoginPassword;
    @BindView(R.id.User_Login_ForgetPassword)
    TextView UserLoginForgetPassword;
    @BindView(R.id.User_Login_Signin)
    Button UserLoginSignin;
    @BindView(R.id.User_Create_Register)
    TextView UserCreateRegister;
    Unbinder unbinder;
    private ApiServicesSale apiServices;


    public User_Login_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user__login_, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.User_Login_ForgetPassword, R.id.User_Login_Signin, R.id.User_Create_Register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.User_Login_ForgetPassword:
                User_Forget_Password_Fragment user_forget_password_fragment = new User_Forget_Password_Fragment();
                HelperMethod.replace(user_forget_password_fragment, getActivity().getSupportFragmentManager(), R.id.User_Home_Cycle_Frame, null, null);
                break;
            case R.id.User_Login_Signin:
                Intent i = new Intent(getActivity().getApplication(),Home_Sale_Cycle_Activty.class);
                startActivity(i);
                signIn();

                break;
            case R.id.User_Create_Register:
                Intent m = new Intent(getActivity().getApplication(), RegisterRestaurantActivity.class);
                startActivity(m);

//                User_Register_Fragment user_register_fragment = new User_Register_Fragment();
//                HelperMethod.replace(user_register_fragment, getActivity().getSupportFragmentManager(), R.id.User_Home_Cycle_Frame, null, null);
                break;
        }
    }


    private void signIn() {
        String email = UserLoginEmail.getText().toString();
        String password = UserLoginPassword.getEditText().getText().toString();
        if (email.isEmpty()) {
            UserLoginEmail.setError(" please enter your email ? ");
        } else if (password.isEmpty()) {
            UserLoginForgetPassword.setError(" please enter your password ? ");
        } else {
            apiServices.getallemail(email, password).enqueue(new Callback<RestaurantLogin>() {
                @Override
                public void onResponse(Call<RestaurantLogin> call, Response<RestaurantLogin> response) {
                    if (response.body().getStatus() == 1) {
                        try {


                            SharedPreferenceManager.SaveData(getActivity(), "apiServices", response.body().getData().getEmail());

                            Intent intent = new Intent(getActivity(), Home_Sale_Cycle_Activty.class);
                            getActivity().startActivity(intent);
                            getActivity().finish();
                            RestaurantToken();

                        } catch (Exception e) {
                            Log.i(e.getMessage(), "data: ");
                        }
                    }
                }

                @Override
                public void onFailure(Call<RestaurantLogin> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void RestaurantToken() {

        apiServices.RestaurantToken("dCQbnfNht3I:APA91bFwrs3Qjgvco9QQDMkb861nkEm_kiyE6f80AJw_Jkr9uQCcDGLdBTgBXXr_",
                "Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx","android").enqueue(new Callback<NotifyToken>() {
            @Override
            public void onResponse(Call<NotifyToken> call, Response<NotifyToken> response) {
                if (response.body().getStatus()==1) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotifyToken> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
