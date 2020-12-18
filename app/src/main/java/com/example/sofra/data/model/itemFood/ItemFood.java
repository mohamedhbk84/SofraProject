
package com.example.sofra.data.model.itemFood;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemFood {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ItemFoodPagination data;

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

    public ItemFoodPagination getData() {
        return data;
    }

    public void setData(ItemFoodPagination data) {
        this.data = data;
    }

}
