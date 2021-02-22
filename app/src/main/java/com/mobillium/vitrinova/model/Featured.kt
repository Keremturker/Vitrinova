package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName

data class Featured(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("featured")
    val featured: List<FeaturedList>


) {

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

    )

}
