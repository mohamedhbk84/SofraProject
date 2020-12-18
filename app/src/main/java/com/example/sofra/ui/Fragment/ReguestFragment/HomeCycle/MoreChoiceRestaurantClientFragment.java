package com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.notifyToken.NotifyToken;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.ContactMeRestaurantFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.GetOfferRestaurantClientFragment;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.About_appFragment;
import com.google.firebase.iid.FirebaseInstanceId;

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
public class MoreChoiceRestaurantClientFragment extends Fragment {


    @BindView(R.id.Present)
    TextView Present;
    @BindView(R.id.connect_Me)
    TextView connectMe;
    @BindView(R.id.about_app)
    TextView aboutApp;

    @BindView(R.id.exit)
    TextView exit;
    Unbinder unbinder;
    private ApiServicesSale apiServices;

    public MoreChoiceRestaurantClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_choice_restaurant_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Present, R.id.connect_Me, R.id.about_app, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Present:
                GetOfferRestaurantClientFragment getOfferRestaurantClientFragment = new GetOfferRestaurantClientFragment();
                HelperMethod.replace(getOfferRestaurantClientFragment, getActivity().getSupportFragmentManager(), R.id.Reguest_Home_Cycle_Frame, null, null);

                break;
            case R.id.connect_Me:
                ContactMeRestaurantFragment contactMeRestaurantFragment= new ContactMeRestaurantFragment();
                HelperMethod.replace(contactMeRestaurantFragment, getActivity().getSupportFragmentManager(), R.id.Reguest_Home_Cycle_Frame, null, null);


                break;
            case R.id.about_app:
                About_appFragment about_appFragment = new About_appFragment();
                HelperMethod.replace(about_appFragment,getActivity().getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);

                break;
            case R.id.exit:

                showDialog();

                break;
        }
    }

    private void exittoken() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        apiServices.exittoken(refreshedToken,"K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh").enqueue(new Callback<NotifyToken>() {
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

            }
        });

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(" هل انت تريد الخروج ؟")
                .setIcon(R.drawable.accept)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                        System.exit(0);
                        exittoken();
                        Toast.makeText(getActivity(), "exit", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        }).show().setCanceledOnTouchOutside(false);
    }

}
