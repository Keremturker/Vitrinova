package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.EditorShop
import com.mobillium.vitrinova.util.downloadFromUrl

class EditorShopAdapter(
    var context: Context?,
    var list: ArrayList<EditorShop.Shops>
) : RecyclerView.Adapter<EditorShopAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgEditorShop: ImageView = view.findViewById(R.id.imgEditorShop)
        val imgEditorShopLogo: ImageView = view.findViewById(R.id.imgEditorShopLogo)
        val txtEditorShopName: TextView = view.findViewById(R.id.txtEditorShopName)
        val txtShopDescription: TextView = view.findViewById(R.id.txtShopDescription)
        val txtProductCount: TextView = view.findViewById(R.id.txtProductCount)
        val cvFollow: CardView = view.findViewById(R.id.cvFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_editor_shop, parent, false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgEditorShop.downloadFromUrl(list[position].cover.url)
        holder.txtEditorShopName.text = list[position].name
        holder.txtShopDescription.text = list[position].definition
        holder.txtProductCount.text = "${list[position].product_count} ÜRÜN"


        if (
            list[position].logo == null) {

            val firstLetter = holder.txtEditorShopName.text[0].toString()
            val generator = ColorGenerator.MATERIAL
            val color = generator.getColor(position)


            val drawable = TextDrawable.builder()
                .buildRound(firstLetter, color)
            holder.imgEditorShopLogo.setImageDrawable(drawable)

        } else {
            Glide.with(context!!).load(list[position].logo!!.url)
                .apply(RequestOptions.circleCropTransform()).into(holder.imgEditorShopLogo)


        }

    }

    override fun getItemCount(): Int {

        return list.size
    }
}