package com.example.sofra.ui.Fragment.SaleFragment.HomeCycle;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.HomeViewPage;
import com.example.sofra.ui.Activity.SaleActivity.Home_Sale_Cycle_Activty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Notes_OrderFragment extends Fragment {


    @BindView(R.id.home_tabs)
    TabLayout homeTabs;
    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    Unbinder unbinder;
    private Home_Sale_Cycle_Activty home_sale_cycle_activty;

    public Home_Notes_OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home__notes__order, container, false);
        unbinder = ButterKnife.bind(this, view);
        home_sale_cycle_activty = (Home_Sale_Cycle_Activty) getActivity();
        homeViewpager.setAdapter(new HomeViewPage(getChildFragmentManager()));
        homeTabs.setupWithViewPager(homeViewpager);
    //  homeTabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        homeTabs.getTabGravity();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
