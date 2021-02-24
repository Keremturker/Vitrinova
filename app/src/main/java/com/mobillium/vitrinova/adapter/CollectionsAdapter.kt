package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.Collections
import com.mobillium.vitrinova.util.downloadFromUrl

class CollectionsAdapter(var context: Context?, var list: ArrayList<Collections.CollectionsList>) :
    RecyclerView.Adapter<CollectionsAdapter.ViewHolder>() {


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgKoleksiyon: ImageView = view.findViewById(R.id.imgKoleksiyon)
        val txtKoleksiyonTitle: TextView = view.findViewById(R.id.txtCollectionsTitle)
        val txtKoleksiyonDefination: TextView = view.findViewById(R.id.txtKoleksiyonDefination)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)

        return ViewHolder(inflater)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgKoleksiyon.downloadFromUrl(list[position].cover.url)
        holder.txtKoleksiyonTitle.text = list[position].title
        holder.txtKoleksiyonDefination.text = list[position].definition


    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun updateList(newContext:  Context?, newList: List<Collections.CollectionsList>) {

        list.clear()
        list.addAll(newList)
        context = newContext

        notifyDataSetChanged()

    }


}