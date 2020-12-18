
package com.example.sofra.data.model.cities;

import com.example.sofra.data.model.cities1.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//import com.sofra.sofra.data.model.Generated.DataPagination;

public class Cities {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
