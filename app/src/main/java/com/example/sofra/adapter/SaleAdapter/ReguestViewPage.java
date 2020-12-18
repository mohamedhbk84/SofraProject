package com.example.sofra.adapter.SaleAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.Reguesr_INformationStore_DetailesFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.Reguest_Comment_and_Rate_Fragment;
import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.Reguest_Show_FoodmenuFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.CurrentRequests_noteFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.NewRequests_noteFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.PreviousRequests_noteFragment;


public class ReguestViewPage extends FragmentPagerAdapter {

    Fragment fragment [] ={new Reguest_Comment_and_Rate_Fragment(),new Reguesr_INformationStore_DetailesFragment(),new Reguest_Show_FoodmenuFragment()};
    String [] title ={"قائمة الطعام"," التعليقات والتقيم"," معلومات المتجر"};
    public ReguestViewPage(FragmentManager fm) {
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
