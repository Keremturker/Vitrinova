package com.mobillium.vitrinova.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Featured(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("featured")
    val featured: List<FeaturedList>


):Parcelable {

    @Parcelize
    data class FeaturedList(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("cover")
        val cover: Cover,
        @SerializedName("title")
        val title: String?,
        @SerializedName("sub_title")
        val sub_title: String?

    ):Parcelable

}
