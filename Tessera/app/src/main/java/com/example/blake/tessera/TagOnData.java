package com.example.blake.tessera;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagOnData {
        @SerializedName("trip_id")
        @Expose
        private Integer tripId;
        @SerializedName("qr_code")
        @Expose
        private String qrCode;


    public TagOnData(Integer tripId, String qrCode) {
        this.tripId = tripId;
        this.qrCode = qrCode;
    }

    public Integer getTripId() {
            return tripId;
        }

        public void setTripId(Integer tripId) {
            this.tripId = tripId;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
}

