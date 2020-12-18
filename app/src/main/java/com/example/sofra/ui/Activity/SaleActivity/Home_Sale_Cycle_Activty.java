package com.example.sofra.ui.Activity.SaleActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.NotificationClientFragment;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.BranchesAddDetailsFragment;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.Branches_CommissionFragment;
import com.example.sofra.ui.Fragment.SaleFragment.BranchesCycle.Notification_RestaurantFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.Detais_Order_SaleFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.Home_Notes_OrderFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.INFormtion_Personal_DetailsFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.More_Details_About_SofraFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home_Sale_Cycle_Activty extends AppCompatActivity {

    @BindView(R.id.More_Choice_Sale_Cycle)
    ImageButton MoreChoiceSaleCycle;
    @BindView(R.id.Details_Sale_Cycle)
    ImageButton DetailsSaleCycle;
    @BindView(R.id.Client_Reguest_Details_Cycle)
    ImageButton ClientReguestDetailsCycle;
    @BindView(R.id.Show_Details_Food_sale_Details)
    ImageButton ShowDetailsFoodSaleDetails;
    @BindView(R.id.Home_Sale_Frame_Container)
    FrameLayout HomeSaleFrameContainer;

    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.notification)
    ImageView notification;
    @BindView(R.id.toolbarmenucontainer)
    LinearLayout toolbarmenucontainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__sale__cycle__activty);
        ButterKnife.bind(this);

        Detais_Order_SaleFragment detais_order_saleFragment = new Detais_Order_SaleFragment();
        HelperMethod.replace(detais_order_saleFragment, getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Branches_CommissionFragment branches_commissionFragment = new Branches_CommissionFragment();
                HelperMethod.replace(branches_commissionFragment,getSupportFragmentManager(),R.id.Home_Sale_Frame_Container,null,null);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification_RestaurantFragment notification_restaurantFragment = new Notification_RestaurantFragment();
                HelperMethod.replace(notification_restaurantFragment, getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);

            }
        });

    }

    @OnClick({R.id.More_Choice_Sale_Cycle, R.id.Details_Sale_Cycle, R.id.Client_Reguest_Details_Cycle, R.id.Show_Details_Food_sale_Details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.More_Choice_Sale_Cycle:
                More_Details_About_SofraFragment more_details_about_sofraFragment = new More_Details_About_SofraFragment();
                HelperMethod.replace(more_details_about_sofraFragment, getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);

                break;
            case R.id.Details_Sale_Cycle:
                INFormtion_Personal_DetailsFragment inFormtion_personal_detailsFragment = new INFormtion_Personal_DetailsFragment();
                HelperMethod.replace(inFormtion_personal_detailsFragment, getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);

                break;
            case R.id.Client_Reguest_Details_Cycle:
                Home_Notes_OrderFragment home_notes_orderFragment = new Home_Notes_OrderFragment();
                HelperMethod.replace(home_notes_orderFragment, getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
                break;
            case R.id.Show_Details_Food_sale_Details:
                Detais_Order_SaleFragment detais_order_saleFragment = new Detais_Order_SaleFragment();
                HelperMethod.replace(detais_order_saleFragment, getSupportFragmentManager(), R.id.Home_Sale_Frame_Container, null, null);
                break;
//            case R.id.menu:
//                menu.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(Home_Sale_Cycle_Activty.this,Branches_Sale_Cycle_Activity.class));
//                    }
//                });
//          startActivity(new Intent(Home_Sale_Cycle_Activty.this,Branches_Sale_Cycle_Activity.class));

//                break;
            case R.id.notification:

                break;
        }
    }



}
