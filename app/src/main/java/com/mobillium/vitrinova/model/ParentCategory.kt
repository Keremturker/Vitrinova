package com.mobillium.vitrinova.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
):Parcelable
