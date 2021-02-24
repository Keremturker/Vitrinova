package com.mobillium.vitrinova.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shop(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("definition")
    val definition: String?,
    @SerializedName("name_updateable")
    val name_updateable: Boolean?,
    @SerializedName("vacation_mode")
    val vacation_mode: Int?,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("shop_payment_id")
    val shop_payment_id: Int?,
    @SerializedName("product_count")
    val product_count: Int?,
    @SerializedName("shop_rate")
    val shop_rate: Int?,
    @SerializedName("comment_count")
    val comment_count: Int?,
    @SerializedName("follower_count")
    val follower_count: Int?,
    @SerializedName("is_editor_choice")
    val is_editor_choice: Boolean?,
    @SerializedName("is_following")
    val is_following: Boolean?,
    @SerializedName("cover")
    val cover: Cover?,
    @SerializedName("share_url")
    val share_url: String?,
    @SerializedName("logo")
    val logo: Cover?

):Parcelable