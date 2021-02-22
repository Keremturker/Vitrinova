package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName


data class Cover(
    @SerializedName("width")
    val width: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("medium")
    val medium: MediumItem?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailItem?,

    ) {

    data class MediumItem(
        @SerializedName("width")
        val width: Int?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("url")
        val url: String?
    )

    data class ThumbnailItem(
        @SerializedName("width")
        val width: Int?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("url")
        val url: String
    )

}
