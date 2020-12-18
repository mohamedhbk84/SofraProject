package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sofra.R;
import com.example.sofra.adapter.SaleAdapter.ReguestOrderViewPage;
import com.example.sofra.adapter.SaleAdapter.ReguestViewPage;
import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Customer_Home_TabFragment extends Fragment {


    @BindView(R.id.home_tabs1)
    TabLayout homeTabs1;
    @BindView(R.id.home_viewpager1)
    ViewPager homeViewpager1;
    Unbinder unbinder;
    private Reguest_Cycle_detailsActivity reguest_cycle_detailsActivity;
    public Customer_PreviousRequests_noteFragment previousRequests_noteFragment;
    public  Customer_CurrentRequests_noteFragment customer_currentRequests_noteFragment;
    public Customer_Home_TabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer__home__tab, container, false);
        unbinder = ButterKnife.bind(this, view);
        reguest_cycle_detailsActivity = (Reguest_Cycle_detailsActivity) getActivity();
        homeViewpager1.setAdapter(new ReguestOrderViewPage(getChildFragmentManager()));
        homeTabs1.setupWithViewPager(homeViewpager1);
        //  homeTabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        homeTabs1.getTabGravity();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
