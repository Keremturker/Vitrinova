package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.NewShops
import com.mobillium.vitrinova.model.Shop
import com.mobillium.vitrinova.util.downloadFromUrl

class NewShopAdapter(var context: Context?, var list: ArrayList<Shop>) :
    RecyclerView.Adapter<NewShopAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgNewShop: ImageView = view.findViewById(R.id.imgNewShop)
        val imgShopLogo: ImageView = view.findViewById(R.id.imgShopLogo)
        val txtShopName: TextView = view.findViewById(R.id.txtShopName)
        val txtShopDescription: TextView = view.findViewById(R.id.txtShopDescription)
        val txtProductCount: TextView = view.findViewById(R.id.txtProductCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_shop, parent, false)

        return ViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.txtShopName.text = list[position].name
        holder.txtShopDescription.text = list[position].definition
        holder.txtProductCount.text = " ${list[position].product_count} ÜRÜN"



        if (list[position].cover == null) {

            holder.imgNewShop.setImageResource(R.drawable.no_banner)

        } else {
            holder.imgNewShop.downloadFromUrl(list[position].cover!!.url)

        }



        if (
            list[position].logo == null) {

            val firstLetter = holder.txtShopName.text[0].toString()
            val generator = ColorGenerator.MATERIAL
            val color = generator.getColor(position)


            val drawable = TextDrawable.builder()
                .buildRound(firstLetter, color)
            holder.imgShopLogo.setImageDrawable(drawable)

        } else {
            Glide.with(context!!).load(list[position].logo!!.url)
                .apply(RequestOptions.circleCropTransform()).into(holder.imgShopLogo)


        }


    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun updateList(newContext:  Context?, newList: List<Shop>) {

        list.clear()
        list.addAll(newList)
        context = newContext
        notifyDataSetChanged()

    }
}