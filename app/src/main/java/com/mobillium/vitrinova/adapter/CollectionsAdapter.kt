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

        val imgCollection: ImageView = view.findViewById(R.id.imgCollection)
        val txtCollectionsTitle: TextView = view.findViewById(R.id.txtCollectionsTitle)
        val txtCollectionDefinition: TextView = view.findViewById(R.id.txtCollectionDefinition)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)

        return ViewHolder(inflater)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgCollection.downloadFromUrl(list[position].cover.url)
        holder.txtCollectionsTitle.text = list[position].title
        holder.txtCollectionDefinition.text = list[position].definition


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