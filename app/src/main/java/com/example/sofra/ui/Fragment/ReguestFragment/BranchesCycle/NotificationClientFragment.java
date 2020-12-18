package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.Notification_ClientAdapter;
import com.example.sofra.adapter.SaleAdapter.ReguestOrderAcceptOrRejectFoodAdapter;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.notifications.DataNotify;
import com.example.sofra.data.model.notifications.Notifications;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationClientFragment extends Fragment {


    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    Unbinder unbinder;
    private ApiServicesSale apiServices;
    private List<DataNotify> notification_data = new ArrayList<>();
    private Notification_ClientAdapter notification_clientAdapter;
    private int page;

    public NotificationClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        getnotification();
        getallrecycler();
        return view;
    }

    private void getallrecycler() {
        LinearLayoutManager mang = new LinearLayoutManager(getActivity());
        RecyclerView.setLayoutManager(mang);
        notification_clientAdapter = new Notification_ClientAdapter(getContext(),getActivity(),notification_data);
        RecyclerView.setAdapter(notification_clientAdapter);
    }

    private void getnotification() {
apiServices.getNotifications("K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh",page).enqueue(new Callback<Notifications>() {
    @Override
    public void onResponse(Call<Notifications> call, Response<Notifications> response) {
        if (response.body().getStatus()==1) {
            List<DataNotify> data = response.body().getData().getData();
            notification_data.addAll(data);
            notification_clientAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<Notifications> call, Throwable t) {

    }
});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
