package com.mobillium.vitrinova.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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

    ) : Parcelable {

    @Parcelize
    data class MediumItem(
        @SerializedName("width")
        val width: Int?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("url")
        val url: String?
    ) : Parcelable

    @Parcelize
    data class ThumbnailItem(
        @SerializedName("width")
        val width: Int?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("url")
        val url: String
    ) : Parcelable

}
