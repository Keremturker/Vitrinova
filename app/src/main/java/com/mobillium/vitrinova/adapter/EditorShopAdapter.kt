package com.mobillium.vitrinova.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.EditorShop
import com.mobillium.vitrinova.util.downloadFromUrl

class EditorShopAdapter(var context: Context, var list: ArrayList<EditorShop.Shops>) :
    RecyclerView.Adapter<EditorShopAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgShopLogo: ImageView = view.findViewById(R.id.imgShopLogo)
        val txtShopName: TextView = view.findViewById(R.id.txtShopName)
        val txtShopDescription: TextView = view.findViewById(R.id.txtShopDescription)
        val imgPopular1: ImageView = view.findViewById(R.id.imgPopular1)
        val imgPopular2: ImageView = view.findViewById(R.id.imgPopular2)
        val imgPopular3: ImageView = view.findViewById(R.id.imgPopular3)
        val btnEditorShop: Button = view.findViewById(R.id.btnEditorShop)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val infaler =
            LayoutInflater.from(parent.context).inflate(R.layout.item_editor_shop, parent, false)
        return ViewHolder(infaler)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        Glide.with(context).load(list[position].logo?.url)
            .apply(RequestOptions.circleCropTransform()).into(holder.imgShopLogo)
        holder.imgPopular1.downloadFromUrl(list[position].popular_products[0].images[0].url)
        holder.imgPopular2.downloadFromUrl(list[position].popular_products[1].images[0].url)
        holder.imgPopular3.downloadFromUrl(list[position].popular_products[2].images[0].url)

        holder.txtShopName.text = list[position].name
        holder.txtShopDescription.text = list[position].definition


    }

    override fun getItemCount(): Int {

        return list.size
    }


    fun updateList(newList: List<EditorShop.Shops>) {

        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }
}