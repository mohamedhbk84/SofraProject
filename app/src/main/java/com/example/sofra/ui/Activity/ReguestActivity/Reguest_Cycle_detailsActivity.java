package com.example.sofra.ui.Activity.ReguestActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.Customer_Home_TabFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.INShare_Private_OrderFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle.NotificationClientFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.MoreChoiceRestaurantClientFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.Person_InformationAndEditFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.Reguest_Show_Details_OrderFragment;
import com.example.sofra.ui.Fragment.ReguestFragment.Rguest_Note_OrderFragment;
import com.example.sofra.ui.Fragment.SaleFragment.HomeCycle.More_Details_About_SofraFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Reguest_Cycle_detailsActivity extends AppCompatActivity {
public INShare_Private_OrderFragment inShare_private_orderFragment;
    @BindView(R.id.menu)
    ImageButton menu;
    @BindView(R.id.App_Bar_TextViewChange)
    TextView AppBarTextViewChange;
    @BindView(R.id.notification)
    ImageButton notification;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.Reguest_Home_Cycle_Frame)
    FrameLayout ReguestHomeCycleFrame;
    @BindView(R.id.More_Choice_Sale_Cycle)
    ImageButton MoreChoiceSaleCycle;
    @BindView(R.id.Details_Sale_Cycle)
    ImageButton DetailsSaleCycle;
    @BindView(R.id.Client_Reguest_Details_Cycle)
    ImageButton ClientReguestDetailsCycle;
    @BindView(R.id.Show_Details_Food_sale_Details)
    ImageButton ShowDetailsFoodSaleDetails;
    @BindView(R.id.toolbarmenucontainer)
    LinearLayout toolbarmenucontainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reguest__cycle_details);
        ButterKnife.bind(this);
        Reguest_Show_Details_OrderFragment reguest_show_details_orderFragment = new Reguest_Show_Details_OrderFragment();
        HelperMethod.replace(reguest_show_details_orderFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);
    }

    @OnClick({R.id.menu, R.id.notification, R.id.More_Choice_Sale_Cycle, R.id.Details_Sale_Cycle, R.id.Client_Reguest_Details_Cycle, R.id.Show_Details_Food_sale_Details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                INShare_Private_OrderFragment inShare_private_orderFragment = new INShare_Private_OrderFragment();
                HelperMethod.replace(inShare_private_orderFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);

                break;
            case R.id.notification:
                NotificationClientFragment notificationClientFragment = new NotificationClientFragment();
                HelperMethod.replace(notificationClientFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);
                break;
            case R.id.More_Choice_Sale_Cycle:
                MoreChoiceRestaurantClientFragment moreChoiceRestaurantClientFragment = new MoreChoiceRestaurantClientFragment();
                HelperMethod.replace(moreChoiceRestaurantClientFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);

                break;
            case R.id.Details_Sale_Cycle:
                Person_InformationAndEditFragment person_informationAndEditFragment = new Person_InformationAndEditFragment();
                HelperMethod.replace(person_informationAndEditFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);

                break;
            case R.id.Client_Reguest_Details_Cycle:
                Customer_Home_TabFragment customer_home_tabFragment= new Customer_Home_TabFragment();
                HelperMethod.replace(customer_home_tabFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);
//                Rguest_Note_OrderFragment rguest_note_orderFragment = new Rguest_Note_OrderFragment();
//                HelperMethod.replace(rguest_note_orderFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);
                break;
            case R.id.Show_Details_Food_sale_Details:
                Reguest_Show_Details_OrderFragment reguest_show_details_orderFragment = new Reguest_Show_Details_OrderFragment();
                HelperMethod.replace(reguest_show_details_orderFragment,getSupportFragmentManager(),R.id.Reguest_Home_Cycle_Frame,null,null);
                break;
        }
    }
}
