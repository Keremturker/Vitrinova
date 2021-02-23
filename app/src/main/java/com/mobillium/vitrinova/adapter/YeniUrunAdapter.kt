package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.Products
import com.mobillium.vitrinova.util.downloadFromUrl

class YeniUrunAdapter(var context: Context, var list: ArrayList<Products.ProductsList>) :
    RecyclerView.Adapter<YeniUrunAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgYeniUrun: ImageView = view.findViewById(R.id.imgYeniUrun)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtsubTitle: TextView = view.findViewById(R.id.txtsubTitle)
        val txtFiyat: TextView = view.findViewById(R.id.txtFiyat)
        val txtEskiFiyat: TextView = view.findViewById(R.id.txtEskiFiyat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_yeni_urun, parent, false)

        return ViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgYeniUrun.downloadFromUrl(list[position].images[0].url)
        holder.txtTitle.text = list[position].title
        holder.txtsubTitle.text = list[position].shop.name
        holder.txtFiyat.text = list[position].priceInt
        holder.txtEskiFiyat.text = list[position].old_price


    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun updateList(newList: List<Products.ProductsList>) {

        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }
}