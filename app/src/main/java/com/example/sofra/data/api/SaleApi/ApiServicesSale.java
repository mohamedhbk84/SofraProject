package com.example.sofra.data.api.SaleApi;

import com.example.sofra.data.model.acceptOrder.AcceptOrder;
import com.example.sofra.data.model.addContact.AddContact;
import com.example.sofra.data.model.categories.Categories;
import com.example.sofra.data.model.cities.Cities;
import com.example.sofra.data.model.cities1.Cities1;
import com.example.sofra.data.model.clientRegister.ClientRegister;
import com.example.sofra.data.model.client_login.ClientLogin;
import com.example.sofra.data.model.client_restaurant_review.ClientRestaurantReview;
import com.example.sofra.data.model.generalData.GeneralObject;

import com.example.sofra.data.model.getOffersClient.GetOffersClient;
import com.example.sofra.data.model.informationRestaurant.InformationRestaurant;
import com.example.sofra.data.model.itemFood.ItemFood;

import com.example.sofra.data.model.listoforders.ListOfOrders;
import com.example.sofra.data.model.newOrder.NewOrder;
import com.example.sofra.data.model.newPassword.NewPassword;
import com.example.sofra.data.model.notifications.Notifications;
import com.example.sofra.data.model.notifyToken.NotifyToken;
import com.example.sofra.data.model.offer.Offer;
import com.example.sofra.data.model.offers.Offers;
import com.example.sofra.data.model.regions.Regions;
import com.example.sofra.data.model.rejectOrder.RejectOrder;
import com.example.sofra.data.model.resetPassword.ResetPassword;
import com.example.sofra.data.model.restaurant.Restaurant;
import com.example.sofra.data.model.restaurantRegister.RestaurantRegister;
import com.example.sofra.data.model.restaurant_login.RestaurantLogin;


import com.example.sofra.data.model.restaurant_new_item.RestaurantNewItem;
import com.example.sofra.data.model.restaurant_reject_order.RestaurantRejectOrder;
import com.example.sofra.data.model.restaurant_reviews.RestaurantReviews;
import com.example.sofra.data.model.restaurant_update_item.RestaurantUpdateItem;
import com.example.sofra.data.model.restaurant_update_offer.RestaurantUpdateOffer;
import com.example.sofra.data.model.restaurantcommissions.RestaurantCommissions;
import com.example.sofra.data.model.restaurantmyorders.RestaurantMyOrders;
import com.example.sofra.data.model.restaurantprofile.RestaurantProfile;
import com.example.sofra.data.model.restaurants.Restaurants;
import com.example.sofra.data.model.showOrder.ShowOrder;
import com.example.sofra.data.model.updateProfileClient.ProfileClient;
import com.example.sofra.model.my_item.MyItem;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServicesSale {

    @GET("restaurant/my-items")
    Call<MyItem> getAllItems(@Query("api_token") String api_token,
                             @Query("page") int page);

    @POST("restaurant/accept-order")
    @FormUrlEncoded
    Call<AcceptOrder> acceptOrder(@Field("api_token") String api_token, @Field("order_id") int order_id);


    @GET("restaurant/my-items")
    Call<ItemFood> getallCurrentItem(@Query("api_token") String api_token,
                                     @Query("page") int page);


    @POST("restaurant/login")
    @FormUrlEncoded
    Call<RestaurantLogin> getallemail(@Field("email") String email,
                                      @Field("password") String password);

    @POST("restaurant/reset-password")
    @FormUrlEncoded
    Call<ResetPassword> resetPassword(@Field("email") String email);

    @POST("restaurant/new-password")
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("password") String password, @Field("password_confirmation") String password_confirmation
            , @Field("code") String pin_code);


    @POST("restaurant/update-item")
    @Multipart
    Call<RestaurantUpdateItem> updateProduct(@Part("description") RequestBody description,
                                             @Part("price") RequestBody price,
                                             @Part("preparing_time") RequestBody preparing_time,
                                             @Part("item_id") RequestBody item_id,
                                             @Part("name") RequestBody name,
                                             @Part("api_token") RequestBody api_token,
                                             @Part MultipartBody.Part photo);


    @POST("restaurant/update-item")
    @Multipart
    Call<RestaurantUpdateItem> updateProduct(@Part("description") RequestBody description,
                                             @Part("price") RequestBody price,
                                             @Part("preparing_time") RequestBody preparing_time,
                                             @Part("item_id") RequestBody item_id,
                                             @Part("name") RequestBody name,
                                             @Part("api_token") RequestBody api_token);


    @POST("restaurant/new-item")
    @Multipart
    Call<RestaurantNewItem> AddProduct(@Part("description") RequestBody description,
                                       @Part("price") RequestBody price,
                                       @Part("preparing_time") RequestBody preparing_time,
                                       @Part("name") RequestBody name,
                                       @Part("api_token") RequestBody api_token,
                                       @Part MultipartBody.Part photo);


    @GET("restaurant/commissions")
    Call<RestaurantCommissions> commision(@Query("api_token") String api_token);


    @POST("restaurant/update-offer")
    @Multipart
    Call<RestaurantUpdateOffer> getAALlOffer(@Part("description") RequestBody description,
                                             @Part("starting_at") RequestBody starting_at,
                                             @Part("price") RequestBody price,
                                             @Part("ending_at") RequestBody ending_at,
                                             @Part("offer_id") RequestBody offer_id,
                                             @Part("name") RequestBody name,
                                             @Part("api_token") RequestBody api_token,
                                             @Part MultipartBody.Part photo
    );

    @POST("restaurant/update-offer")
    @Multipart
    Call<RestaurantUpdateOffer> getAALlOffer(@Part("description") RequestBody description,
                                             @Part("starting_at") RequestBody starting_at,
                                             @Part("price") RequestBody price,
                                             @Part("ending_at") RequestBody ending_at,
                                             @Part("offer_id") RequestBody offer_id,
                                             @Part("name") RequestBody name,
                                             @Part("api_token") RequestBody api_token);




    @Multipart
    @POST("client/profile")
    Call<ProfileClient> showEditProfileClient(@Part("api_token") RequestBody api_token, @Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("phone") RequestBody phone , @Part("password") RequestBody password
            , @Part("password_confirmation") RequestBody password_confirmation
            , @Part("address") RequestBody address, @Part("region_id") RequestBody region_id
            , @Part MultipartBody.Part image);
    @Multipart
    @POST("client/profile")
    Call<ProfileClient> showEditProfileClient(@Part("api_token") RequestBody api_token, @Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("phone") RequestBody phone , @Part("password") RequestBody password
            , @Part("password_confirmation") RequestBody password_confirmation
            , @Part("address") RequestBody address, @Part("region_id") RequestBody region_id);



    @FormUrlEncoded
    @POST("restaurant/profile")
    Call<RestaurantProfile> getProfile(@Field("api_token") String api_token);


    @GET("governorates")
    Call<GeneralObject> getGovernorates();

    @GET("cities")
    Call<GeneralObject> getCities();

    @GET("offers")
    Call<Offers> getOffers(@Query("restaurant_id") int restaurant_id,
                           @Query("page") int page);

    @GET("offer")
    Call<Offer> getoffer(@Query("offer_id") int offer_id);

    @FormUrlEncoded
    @POST("restaurant/reject-order")
    Call<RestaurantRejectOrder> reject(
            @Field("api_token") String api_token,
            @Field("order_id") int order_id);

    @GET("restaurant/my-offers")
    Call<Offers> getallOffers(@Query("api_token") String api_token,
                              @Query("page") int page);


    @GET("restaurants")
    Call<Restaurants> getAllRestaurants(@Query("keywork")String keywork,@Query("region_id")int region_id,
            @Query("page") int page);

    @GET("restaurants")
    Call<Restaurants> getAllFilter(
            @Query("category_id") int category_id);

    @GET("items")
    Call<ItemFood> getAllFoodmenu(
            @Query("restaurant_id") int restaurant_id);

    @GET("restaurant")
    Call<Restaurant> getAllDetails(
            @Query("restaurant_id") int restaurant_id);

    @GET("restaurant/reviews")
    Call<RestaurantReviews> getallComment(@Query("api_token") String api_token,
                                          @Query("restaurant_id") int restaurant_id,
                                          @Query("page") int page );

    @GET("cities")
    Call<Cities> getcity();

    @GET("categories")
    Call<GeneralObject> getcategories();

    @POST("client/restaurant/review")
    @FormUrlEncoded
    Call<ClientRestaurantReview> getallReview(
                                                 @Field("api_token") String api_token,
                                                 @Field("comment") String comment,
                                                 @Field("rate") float rate,
                                                 @Field("restaurant_id") int restaurant_id);

    @GET("client/my-orders")
    Call<ListOfOrders> getListOfOrder(@Query("api_token") String api_token,
                                    @Query("state") String state,
                                    @Query("page") int page);
    @GET("restaurant/my-orders")
    Call<RestaurantMyOrders> getAllOrders(@Query("api_token") String api_token,
                                          @Query("state") String state,
                                          @Query("page") int page);



    @Multipart
    @POST("restaurant/register")
    Call<RestaurantRegister> getRestaurantRegister(@Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody password_confirmation
            , @Part("phone") RequestBody phone, @Part("whatsapp") RequestBody whatsapp, @Part("region_id") RequestBody region_id
            , @Part("categories[]") List<RequestBody> categories, @Part("minimum_charger") RequestBody minimum_charger, @Part("delivery_cost") RequestBody delivery_cost,
                                                   @Part MultipartBody.Part image);
    @Multipart
    @POST("restaurant/register")
    Call<RestaurantRegister> getRestaurantRegister(@Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody password_confirmation
            , @Part("phone") RequestBody phone, @Part("whatsapp") RequestBody whatsapp, @Part("region_id") RequestBody region_id
            , @Part("categories[]") List<RequestBody> categories, @Part("minimum_charger") RequestBody minimum_charger, @Part("delivery_cost") RequestBody delivery_cost);





    @Multipart
    @POST("client/sign-up")
    Call<ClientRegister> clientregister(@Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody password_confirmation
            , @Part("phone") RequestBody phone, @Part("address") RequestBody address, @Part("region_id") RequestBody region_id
            , @Part MultipartBody.Part image);


    @POST("client/new-order")
    @FormUrlEncoded
    Call<NewOrder>getnewOrder(
            @Field("api_token") String api_token , @Field("restaurant_id") int restaurant_id
            , @Field("note") String note  , @Field("address") String address
            , @Field("payment_method_id") int payment_method_id , @Field("phone") String phone, @Field("name") String name
            , @Field("items[]")  List<Integer>  items
            , @Field("quantities[]")  List<Integer> quantities, @Field("notes[]")  List<String>  notes );



    @GET("client/show-order")
    Call<ShowOrder> ShowOrder(@Query("api_token") String api_token, @Query("order_id") int order_id);

    @POST("client/profile")
    @FormUrlEncoded
    Call<ProfileClient> getProfileClient(@Field("api_token") String api_token );

    @GET("categories")
    Call<Categories> getCategories();

    @POST("contact")
    @FormUrlEncoded
    Call<AddContact>addContact(@Field("name") String name, @Field("email") String email, @Field("phone") String phone
            , @Field("type") String type, @Field("content") String content);

    @GET("regions")
    Call<Regions> getRegions(@Query("city_id") int city_id);


    @GET("offers")
    Call<GetOffersClient> getOffers(@Query("page") int page);


    @POST("client/decline-order")
    @FormUrlEncoded
    Call<RejectOrder> rejectOrder(@Field("api_token") String api_token, @Field("order_id") int order_id);

    @POST("client/confirm-order")
    @FormUrlEncoded
    Call<RejectOrder> confirmOrder(@Field("api_token") String api_token, @Field("order_id") int order_id);

    @GET("restaurant")
    Call<InformationRestaurant> getinformationrestaurant(@Query("restaurant_id")int restaurant_id);


    @GET("client/notifications")
    Call<Notifications> getNotifications(@Query("api_token") String api_token, @Query("page") int page);
    @GET("restaurant/notifications")
    Call<Notifications> RestaurantNotifications(@Query("api_token") String api_token);


    @POST("client/register-token")
    @FormUrlEncoded
    Call<NotifyToken>LoginToken(@Field("token") String token
            , @Field("api_token") String api_token, @Field("type") String type);

    @POST("client/remove-token")
    @FormUrlEncoded
    Call<NotifyToken>exittoken(@Field("token") String token
            , @Field("api_token") String api_token);


    @POST("restaurant/remove-token")
    @FormUrlEncoded
    Call<NotifyToken>RemoveToken(@Field("token") String token, @Field("api_token") String api_token);

    @POST("restaurant/register-token")
    @FormUrlEncoded
    Call<NotifyToken>RestaurantToken(@Field("token") String token
            , @Field("api_token") String api_token, @Field("type") String type);

    @Multipart
    @POST("restaurant/profile")
    Call<RestaurantRegister> getEditRestaurantRegister(@Part("name") RequestBody name, @Part("email") RequestBody email
            , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody password_confirmation
            , @Part("phone") RequestBody phone, @Part("whatsapp") RequestBody whatsapp, @Part("region_id") RequestBody region_id
            , @Part("categories[]") List<RequestBody> categories, @Part("minimum_charger") RequestBody minimum_charger
            , @Part("delivery_cost") RequestBody delivery_cost, @Part MultipartBody.Part image,
                                                        @Part("api_token") RequestBody api_token,@Part("availability") RequestBody availability);



    @POST("client/login")
    @FormUrlEncoded
    Call<ClientLogin> onLogin(@Field("email") String phone, @Field("password") String password);

}
