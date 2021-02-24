package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.Products
import com.mobillium.vitrinova.util.downloadFromUrl
import com.mobillium.vitrinova.view.MainActivity

class NewProductAdapter(var context: Context?, var list: ArrayList<Products.ProductsList>) :
    RecyclerView.Adapter<NewProductAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgNewProduct: ImageView = view.findViewById(R.id.imgNewProduct)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtSubTitle: TextView = view.findViewById(R.id.txtSubTitle)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtOldPrice: TextView = view.findViewById(R.id.txtOldPrice)
        val cvNewProduct: CardView = view.findViewById(R.id.cvNewProduct)

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

        setAnimation(holder.cvNewProduct)
        holder.imgNewProduct.downloadFromUrl(list[position].images[0].url)
        holder.txtTitle.text = list[position].title
        holder.txtSubTitle.text = list[position].shop.name

        list[position].priceInt?.let {
            holder.txtPrice.text = "$it TL"
        }

        list[position].old_price?.let {

            holder.txtOldPrice.text = "$it TL"
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setAnimation(view: View) {

        val anim = android.view.animation.AnimationUtils.loadAnimation(
            context,
            R.anim.item_animation_from_right
        )
        view.startAnimation(anim)

    }

    fun updateList(newContext: Context?, newList: List<Products.ProductsList>) {

        list.clear()
        list.addAll(newList)
        context = newContext
        notifyDataSetChanged()

    }
}