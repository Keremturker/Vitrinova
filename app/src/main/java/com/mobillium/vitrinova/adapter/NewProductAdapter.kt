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
import com.mobillium.vitrinova.view.MainActivity

class NewProductAdapter(var context: Context?, var list: ArrayList<Products.ProductsList>) :
    RecyclerView.Adapter<NewProductAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgYeniUrun: ImageView = view.findViewById(R.id.imgYeniUrun)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtsubTitle: TextView = view.findViewById(R.id.txtsubTitle)
        val txtFiyat: TextView = view.findViewById(R.id.txtFiyat)
        val txtEskiFiyat: TextView = view.findViewById(R.id.txtEskiFiyat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        var inflater: View

        //Main Fragment
        if (MainActivity.navController.currentDestination?.id == R.id.mainFragment) {
            inflater = LayoutInflater.from(parent.context).inflate(
                R.layout.item_new_product,
                parent,
                false
            )


        } else { //NewProductFragment

            inflater =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_new_product, parent, false)


        }




        return ViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgYeniUrun.downloadFromUrl(list[position].images[0].url)
        holder.txtTitle.text = list[position].title
        holder.txtsubTitle.text = list[position].shop.name

        list[position].priceInt?.let {
            holder.txtFiyat.text = "$it TL"
        }

        list[position].old_price?.let {

            holder.txtEskiFiyat.text = "$it TL"
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun updateList(newContext: Context?, newList: List<Products.ProductsList>) {

        list.clear()
        list.addAll(newList)
        context = newContext
        notifyDataSetChanged()

    }
}