package com.example.blake.tessera;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class TagOnReturn {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("user_current_bal")
    @Expose
    private Double userCurrentBal;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Double getUserCurrentBal() {
        return userCurrentBal;
    }

    public void setUserCurrentBal(Double userCurrentBal) {
        this.userCurrentBal = userCurrentBal;
    }

}
