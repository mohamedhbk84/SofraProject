package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


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
import com.example.sofra.data.model.client_login.ClientLogin;
import com.example.sofra.data.model.notifyToken.NotifyToken;
import com.example.sofra.data.model.restaurant_login.RestaurantLogin;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;
import com.example.sofra.ui.Fragment.SaleFragment.UserCycle.User_Forget_Password_Fragment;

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
public class LogInClientFragment extends Fragment {


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

    public LogInClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.User_Login_ForgetPassword, R.id.User_Create_Register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.User_Login_ForgetPassword:
                User_Forget_Password_Fragment user_forget_password_fragment = new User_Forget_Password_Fragment();
                HelperMethod.replace(user_forget_password_fragment, getActivity().getSupportFragmentManager(), R.id.Frame_login_client, null, null);

                break;
            case R.id.User_Create_Register:
                RegisterClientFragment registerClientFragment = new RegisterClientFragment();
                HelperMethod.replace(registerClientFragment, getActivity().getSupportFragmentManager(), R.id.Frame_login_client, null, null);
                break;
        }
    }

    @OnClick(R.id.User_Login_Signin)
    public void onViewClicked() {
        signIn();
    }
    private void signIn() {
        String email = UserLoginEmail.getText().toString();
        String password = UserLoginPassword.getEditText().getText().toString();
        if (email.isEmpty()) {
            UserLoginEmail.setError(" please enter your email ? ");
        } else if (password.isEmpty()) {
            UserLoginForgetPassword.setError(" please enter your password ? ");
        } else {
            apiServices.onLogin(email,password ).enqueue(new Callback<ClientLogin>() {
                @Override
                public void onResponse(Call<ClientLogin> call, Response<ClientLogin> response) {
                    if (response.body().getStatus() == 1) {
                        try {

                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            SharedPreferenceManager.SaveData(getActivity(), "apiServices", response.body().getData().getClient().getEmail());

                            Intent intent = new Intent(getActivity(), Reguest_Cycle_detailsActivity.class);
                            getActivity().startActivity(intent);
                            getActivity().finish();
                            getToken();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ClientLogin> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void getToken() {
//        String retrey = FirebaseInstanceId.getInstance().getToken();
apiServices.LoginToken("CY4kw2Ma2aA6x7T5O3UODws1UakXI9vgFVSoY3xUXY","K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh","android").enqueue(new Callback<NotifyToken>() {
    @Override
    public void onResponse(Call<NotifyToken> call, Response<NotifyToken> response) {
        if (response.body().getStatus()==1) {
            try {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    @Override
    public void onFailure(Call<NotifyToken> call, Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

    }
});
    }

}


