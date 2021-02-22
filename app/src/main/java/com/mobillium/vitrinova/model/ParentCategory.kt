package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName

data class ParentCategory(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("parent_id")
    val parent_id: Int?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("parent_category")
    val parent_category: String?
)
