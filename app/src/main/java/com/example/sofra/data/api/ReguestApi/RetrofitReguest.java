package com.example.sofra.data.api.ReguestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitReguest {
     public static final String Base_Url="http://ipda3-tech.com/blood-bank/api/v1/";
     private static Retrofit retrofit =null;

     public static Retrofit getClient(){

   if (retrofit == null){
        retrofit = new Retrofit.Builder()
                  .baseUrl(Base_Url)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();


   }
   return retrofit;

     }

}
