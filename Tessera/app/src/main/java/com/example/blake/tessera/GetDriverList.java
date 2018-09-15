package com.example.blake.tessera;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDriverList {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("pin")
        @Expose
        private Integer pin;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPin() {
            return pin;
        }

        public void setPin(Integer pin) {
            this.pin = pin;
        }
}
