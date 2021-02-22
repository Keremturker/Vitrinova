package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName

data class NewShops(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("shops")
    val shops: List<Shop>
)
