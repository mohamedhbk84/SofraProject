
package com.example.sofra.data.model.updateProfileClient;

import com.example.sofra.data.model.itemFood.ItemFood;
import com.example.sofra.data.model.itemFood.ItemFoodData;
import com.example.sofra.data.model.restaurantprofile.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataProfile {

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
