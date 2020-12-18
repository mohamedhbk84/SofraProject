package com.example.sofra.ui.Activity.SaleActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.Branches_CommissionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Branches_Sale_Cycle_Activity extends AppCompatActivity {


    @BindView(R.id.Branche_Sale_Frame_Container)
    FrameLayout BrancheSaleFrameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches__sale__cycle_);
        ButterKnife.bind(this);

    }


}
