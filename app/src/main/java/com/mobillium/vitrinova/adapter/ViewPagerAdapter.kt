package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.Featured
import com.mobillium.vitrinova.util.downloadFromUrl

class ViewPagerAdapter(
    private val context: Context,
    private val list: ArrayList<Featured.FeaturedList>
) : PagerAdapter() {
    override fun getCount(): Int {

        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.viewpager_item, container, false)

        val imgSlider: ImageView = view.findViewById(R.id.imgSlider)


        imgSlider.downloadFromUrl(list[position].cover.url)

        container.addView(view, position)

        return view


    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }

    fun updateList(newList: List<Featured.FeaturedList>) {

        list.clear()
        list.addAll(newList)

        notifyDataSetChanged()

    }
}