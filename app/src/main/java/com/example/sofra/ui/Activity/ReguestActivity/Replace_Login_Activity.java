package com.example.sofra.ui.Activity.ReguestActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.LogInClientFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Replace_Login_Activity extends AppCompatActivity {

    @BindView(R.id.Frame_login_client)
    FrameLayout FrameLoginClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace__login_);
        ButterKnife.bind(this);

        LogInClientFragment logInClientFragment = new LogInClientFragment();
        HelperMethod.replace(logInClientFragment, getSupportFragmentManager(), R.id.Frame_login_client, null, null);


    }
}
