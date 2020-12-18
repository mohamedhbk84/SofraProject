package com.example.sofra.ui.RoomDatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sofra.R;
import com.example.sofra.data.api.SaleApi.ApiServicesSale;
import com.example.sofra.data.api.SaleApi.RetrofitClient;
import com.example.sofra.data.model.client_restaurant_review.ClientRestaurantReview;
import com.example.sofra.helper.EmotionRating;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.ui.Fragment.ReguestFragment.HomeCycle.Reguest_Comment_and_Rate_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Note_about_AppFragment extends Fragment {


    @BindView(R.id.txt)
    TextView txt;

    @BindView(R.id.Edit_Add_Note)
    EditText EditAddNote;
    @BindView(R.id.Add_Button)
    Button AddButton;
    @BindView(R.id.RE)
    RelativeLayout RE;
    @BindView(R.id.Rating_action)
    EmotionRating RatingAction;
    private ApiServicesSale apiServices;
    private String v;
    private int idRestaurant;

    private float mrating;
    Unbinder unbinder;


    public Add_Note_about_AppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add__note_about__app, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = RetrofitClient.getClient().create(ApiServicesSale.class);
        emotion();
//        sqLiteHelper = new SQLiteHelper(getActivity(),"FoodDB.sqlite",null,1);
//        sqLiteHelper.queryData("Create Table If Not EXISTS FOOD(ID INTEGER PRIMARY KEY AutoINCREMENT,VARCHAR,image BIOG)");

        return view;
    }

    private void emotion() {
        RatingAction.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mrating = rating;
                ratingBar.setRating(rating);
            }
        });
        RatingAction.setRating(1);
    }
//


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.Add_Button)
    public void onViewClicked() {
        add();
        Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
        RE.setVisibility(View.INVISIBLE);

    }

    private void add() {
        apiServices.getallReview("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB", EditAddNote.getText().toString(), mrating, idRestaurant).enqueue(new Callback<ClientRestaurantReview>() {
            @Override
            public void onResponse(Call<ClientRestaurantReview> call, Response<ClientRestaurantReview> response) {

                if (response.body().getStatus() == 1) {
                    try {
                        Reguest_Comment_and_Rate_Fragment reguest_comment_and_rate_fragment = new Reguest_Comment_and_Rate_Fragment();
                        HelperMethod.replace(reguest_comment_and_rate_fragment, getActivity().getSupportFragmentManager(), R.id.Reguest_Home_Cycle_Frame, null, null);

                        Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientRestaurantReview> call, Throwable t) {

            }
        });

    }

}





