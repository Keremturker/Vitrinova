package com.mobillium.vitrinova.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categories(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("categories")
    val categories: List<CategoriesList>
):Parcelable {

    @Parcelize
    data class CategoriesList(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("parent_id")
        val parent_id: Int?,
        @SerializedName("order")
        val order: Int?,
        @SerializedName("parent_category")
        val parent_category: String?,
        @SerializedName("logo")
        val logo: Cover,
        @SerializedName("cover")
        val cover: Cover,
        @SerializedName("children")
        val children: List<Children>
    ):Parcelable {

        @Parcelize
        data class Children(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("parent_id")
            val parent_id: Int?,
            @SerializedName("order")
            val order: Int?,
            @SerializedName("parent_category")
            val parent_category: ParentCategory,
            @SerializedName("logo")
            val logo: Cover,
            @SerializedName("cover")
            val cover: Cover
        ):Parcelable
    }

}
