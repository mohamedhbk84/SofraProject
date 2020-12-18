package com.example.sofra.ui.Fragment.ReguestFragment.BranchesCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.model.itemFood.ItemFoodData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.RoomDatabase.RoomManger;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.sofra.helper.HelperMethod.onLoadImageFromUrl;

//import com.example.sofra.data.model.items.Datum;

/**
 * A simple {@link Fragment} subclass.
 */
public class Private_OrderFragment extends Fragment {

    @BindView(R.id.Image_View_Food_menu)
    ImageView ImageViewFoodMenu;
    @BindView(R.id.Name_Foodmenu)
    TextView NameFoodmenu;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sale_number)
    TextView saleNumber;
    @BindView(R.id.sale)
    TextView sale;
    @BindView(R.id.Linear)
    LinearLayout Linear;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.Add_New_Order)
    EditText AddNewOrder;
    @BindView(R.id.mainase_the_number)
    ImageButton mainaseTheNumber;
    @BindView(R.id.Possitive_the_number)
    ImageButton PossitiveTheNumber;
    @BindView(R.id.l)
    LinearLayout l;
    @BindView(R.id.IMage_Shopping)
    ImageButton IMageShopping;
    @BindView(R.id.Show_Count)
    TextView ShowCount;
    @BindView(R.id.preparing_time)
    TextView preparingTime;
    Unbinder unbinder;

    private ApiServicesSale apiServices;
    private int count;
    public ItemFoodData RestaurantItem;
    RoomManger roomManger;

    private String getName, getDescription, getPrice, getPreparingTime, getPhotoUrl;
    private int idItem;
    private int quantity;
    private boolean checkItems = true;
    private boolean checkIdResruarant = false;
    private INShare_Private_OrderFragment inShare_private_orderFragment;

    public Private_OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_private__order, container, false);
        unbinder = ButterKnife.bind(this, view);
        roomManger = RoomManger.getInstance(getContext());

//          start();
        getData();
        return view;
    }


    private void getData() {
//        Bundle bundle = getArguments();
//        idItem = bundle.getInt("getIdItem");
        NameFoodmenu.setText(RestaurantItem.getName());
        name.setText(RestaurantItem.getDescription());
        saleNumber.setText(RestaurantItem.getPrice());
        preparingTime.setText(RestaurantItem.getPreparingTime() + " دقيقة ");

        onLoadImageFromUrl(ImageViewFoodMenu, RestaurantItem.getPhotoUrl(), getContext());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.IMage_Shopping)
    public void onViewClicked() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

                roomManger = RoomManger.getInstance(getContext());
                roomManger.roomDao().add(RestaurantItem);

                inShare_private_orderFragment = new INShare_Private_OrderFragment();

                inShare_private_orderFragment.itemFoodDataList = roomManger.roomDao().getAll();

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        HelperMethod.replace(inShare_private_orderFragment, getActivity().getSupportFragmentManager(), R.id.Reguest_Home_Cycle_Frame, null, null);
                    }
                });

            }
        });

    }

    @OnClick({R.id.mainase_the_number, R.id.Possitive_the_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mainase_the_number:
                count = Integer.parseInt(ShowCount.getText().toString());
                count++;
                ShowCount.setText("" + count);

                break;
            case R.id.Possitive_the_number:
                count = Integer.parseInt(ShowCount.getText().toString());
                count--;
                ShowCount.setText("" + count);
                break;
        }
    }
}
