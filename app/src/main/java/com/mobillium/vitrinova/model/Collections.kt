package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName

data class Collections(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("collections")
    val collections: List<CollectionsList>

) {

    data class CollectionsList(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("definition")
        val definition: String?,
        @SerializedName("start")
        val start: String?,
        @SerializedName("end")
        val end: String?,
        @SerializedName("share_url")
        val share_url: String?,
        @SerializedName("cover")
        val cover: Cover,
        @SerializedName("logo")
        val logo: Cover
    )

}
