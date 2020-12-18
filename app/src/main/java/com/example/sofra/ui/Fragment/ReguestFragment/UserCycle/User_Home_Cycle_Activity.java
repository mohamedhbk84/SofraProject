package com.example.sofra.ui.Fragment.ReguestFragment.UserCycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.SaleFragment.UserCycle.User_Login_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class User_Home_Cycle_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__home__cycle_);


        User_Login_Fragment fragment = new User_Login_Fragment();
        HelperMethod.replace(fragment, getSupportFragmentManager(), R.id.User_Home_Cycle_Frame, null, null);
    }
}
