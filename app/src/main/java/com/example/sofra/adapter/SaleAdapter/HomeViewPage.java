package com.example.sofra.adapter.SaleAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.CurrentRequests_noteFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.NewRequests_noteFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.PreviousRequests_noteFragment;


public class HomeViewPage extends FragmentPagerAdapter {

    Fragment fragment [] ={new PreviousRequests_noteFragment(),new CurrentRequests_noteFragment(),new NewRequests_noteFragment()};
    String [] title ={"طلبات قديمة","طلبات حالية","طلبات جديدة"};
    public HomeViewPage(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragment[i];
    }

    @Override
    public int getCount() {
        return fragment.length;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
