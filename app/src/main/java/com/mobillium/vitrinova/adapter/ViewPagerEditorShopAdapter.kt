package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.EditorShop
import com.mobillium.vitrinova.util.downloadFromUrl

class ViewPagerEditorShopAdapter(
    var context: Context?,
    var list: ArrayList<EditorShop.Shops>
) :
    PagerAdapter() {

    override fun getCount(): Int {

        return list.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_viewpager_editor_shop, container, false)

        val imgShopLogo: ImageView = view.findViewById(R.id.imgEditorShopLogo)
        val txtShopName: TextView = view.findViewById(R.id.txtEditorShopName)
        val txtShopDescription: TextView = view.findViewById(R.id.txtShopDescription)
        val imgPopular1: ImageView = view.findViewById(R.id.imgPopular1)
        val imgPopular2: ImageView = view.findViewById(R.id.imgPopular2)
        val imgPopular3: ImageView = view.findViewById(R.id.imgPopular3)

        imgPopular1.downloadFromUrl(list[position].popular_products[0].images[0].url)
        imgPopular2.downloadFromUrl(list[position].popular_products[1].images[0].url)
        imgPopular3.downloadFromUrl(list[position].popular_products[2].images[0].url)

        txtShopName.text = list[position].name
        txtShopDescription.text = list[position].definition
        Glide.with(context!!).load(list[position].logo?.url)
            .apply(RequestOptions.circleCropTransform()).into(imgShopLogo)
        container.addView(view, position)
        return view


    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }

    fun updateList(newContext:  Context?, newList: List<EditorShop.Shops>) {

        list.clear()
        list.addAll(newList)
        context = newContext
        notifyDataSetChanged()

    }


}