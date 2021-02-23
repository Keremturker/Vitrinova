package com.mobillium.vitrinova.model

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("products")
    val products: List<ProductsList>


) {

    data class ProductsList(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("code")
        val code: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("definition")
        val definition: String?,
        @SerializedName("old_price")
        val old_price: String?,
        @SerializedName("price")
        val priceInt: String?,
        @SerializedName("stock")
        val stock: Int?,
        @SerializedName("max_installment")
        val max_installment: Int?,
        @SerializedName("commission_rate")
        val commission_rate: Int?,
        @SerializedName("cargo_time")
        val cargo_time: Int?,
        @SerializedName("is_cargo_free")
        val is_cargo_free: Boolean?,
        @SerializedName("is_new")
        val is_new: Boolean?,
        @SerializedName("reject_reason")
        val reject_reason: String?,
        @SerializedName("category_id")
        val category_id: Int?,
        @SerializedName("difference")
        val difference: String?,
        @SerializedName("is_editor_choice")
        val is_editor_choice: Boolean?,
        @SerializedName("comment_count")
        val comment_count: Int?,
        @SerializedName("is_owner")
        val is_owner: Boolean?,
        @SerializedName("is_approved")
        val is_approved: Boolean?,
        @SerializedName("is_active")
        val is_active: Boolean?,
        @SerializedName("share_url")
        val share_url: String?,
        @SerializedName("is_liked")
        val is_liked: Boolean?,
        @SerializedName("like_count")
        val like_count: Int?,
        @SerializedName("shop")
        val shop: Shop,
        @SerializedName("category")
        val category: Category,
        @SerializedName("images")
        val images: List<Cover>
    ) {



        data class Category(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("parent_id")
            val parent_id: Int?,
            @SerializedName("order")
            val order: Int?,
            @SerializedName("parent_category")
            val parent_category: ParentCategory
        )

    }


}
