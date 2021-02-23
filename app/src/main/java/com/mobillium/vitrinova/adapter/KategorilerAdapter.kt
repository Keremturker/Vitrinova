package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.Categories
import com.mobillium.vitrinova.util.downloadFromUrl

class KategorilerAdapter(var context: Context, var list: ArrayList<Categories.CategoriesList>) :
    RecyclerView.Adapter<KategorilerAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgKategori: ImageView = view.findViewById(R.id.imgKategori)
        val txtKategoriTitle: TextView = view.findViewById(R.id.txtKategoriTitle)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_kategori, parent, false)

        return ViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgKategori.downloadFromUrl(list[position].logo.url)
        holder.txtKategoriTitle.text = list[position].name

    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun updateList(newList: List<Categories.CategoriesList>) {

        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }
}