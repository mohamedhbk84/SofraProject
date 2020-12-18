package com.example.sofra.ui.Fragment.SaleFragment.HomeCycle;


import android.app.Activity;
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
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.About_appFragment;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.Branches_New_Present_Fragment;
import com.example.sofra.ui.Fragment.SaleFragment.UserCycle.User_Login_Fragment;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.data.local.SharedPreferenceManager.clean;

/**
 * A simple {@link Fragment} subclass.
 */
public class More_Details_About_SofraFragment extends Fragment {


    @BindView(R.id.th)
    TextView th;
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

    public More_Details_About_SofraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more__details__about__sofra, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Present, R.id.connect_Me, R.id.about_app, R.id.exit})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.Present:
                Branches_New_Present_Fragment branches_new_present_fragment = new Branches_New_Present_Fragment();
                HelperMethod.replace(branches_new_present_fragment, getActivity().getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
                break;
            case R.id.connect_Me:


                break;
            case R.id.about_app:
                About_appFragment about_appFragment = new About_appFragment();
                HelperMethod.replace(about_appFragment,getActivity().getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
                break;

            case R.id.exit:
                clean(getActivity());
                showDialog();
                break;
        }
    }

    private void exittoken() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        apiServices.RemoveToken(refreshedToken,"Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx").enqueue(new Callback<NotifyToken>() {
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
