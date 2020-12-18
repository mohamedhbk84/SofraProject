package com.example.sofra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sofra.ui.Activity.ReguestActivity.Reguest_Cycle_detailsActivity;
import com.example.sofra.ui.Fragment.ReguestFragment.UserCycle.User_Home_Cycle_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.Main_Order_Food)
    Button MainOrderFood;
    @BindView(R.id.Main_Sale_Food)
    Button MainSaleFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Main_Order_Food, R.id.Main_Sale_Food})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Main_Order_Food:
                Intent intent=new Intent(MainActivity.this, Reguest_Cycle_detailsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Main_Sale_Food:

                Intent i=new Intent(MainActivity.this, User_Home_Cycle_Activity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
