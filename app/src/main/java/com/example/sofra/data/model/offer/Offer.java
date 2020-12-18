
package com.example.sofra.data.model.offer;

import com.example.sofra.data.model.itemFood.ItemFoodData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ItemFoodData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ItemFoodData getData() {
        return data;
    }

    public void setData(ItemFoodData data) {
        this.data = data;
    }

}
