package com.example.alsaint.examplemvp.server.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CakeObj {

    @SerializedName("cakes")
    List<Cake> cakes;

    public List<Cake> getCakes() {
        return cakes;
    }

    public class Cake {
        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("previewDescription")
        private String previewDescription;
        @SerializedName("detailDescription")
        private String detailDescription;
        @SerializedName("image")
        private String imageUrl;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getPreviewDescription() {
            return previewDescription;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}