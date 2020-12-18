package com.example.sofra.ui.Fragment.ReguestFragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.HomeViewPage;
import com.example.sofra.adapter.SaleAdapter.ReguestViewPage;
import com.example.sofra.data.model.restaurants.Datum;
import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rguest_Note_OrderFragment extends Fragment {


    public Datum ResturantItem;
    @BindView(R.id.Image_View)
    android.widget.ImageView ImageView;
    @BindView(R.id.Name_Order)
    TextView NameOrder;
    @BindView(R.id.home_tabs1)
    TabLayout homeTabs1;
    @BindView(R.id.home_viewpager1)
    ViewPager homeViewpager1;
    Unbinder unbinder;
    private Reguest_Cycle_detailsActivity reguest_cycle_detailsActivity;

    public Rguest_Note_OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rguest__note__order, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        reguest_cycle_detailsActivity = (Reguest_Cycle_detailsActivity) getActivity();
        homeViewpager1.setAdapter(new ReguestViewPage(getChildFragmentManager()));
        homeTabs1.setupWithViewPager(homeViewpager1);
        //  homeTabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        homeTabs1.getTabGravity();
        return view;
    }

    private void getData() {
        NameOrder.setText(ResturantItem.getName());
        onLoadImageFromUrl(ImageView, ResturantItem.getPhotoUrl(), getContext());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
