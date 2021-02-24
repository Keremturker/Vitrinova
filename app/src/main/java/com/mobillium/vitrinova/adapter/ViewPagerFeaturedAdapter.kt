package com.mobillium.vitrinova.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.Featured
import com.mobillium.vitrinova.util.downloadFromUrl

class ViewPagerFeaturedAdapter(
      var context: Context?,
      var list: ArrayList<Featured.FeaturedList>
) : PagerAdapter() {
    override fun getCount(): Int {

        return list.size
        
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_featured, container, false)

        val imgSlider: ImageView = view.findViewById(R.id.imgSlider)


        imgSlider.downloadFromUrl(list[position].cover.url)

        container.addView(view, position)

        return view


    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }

    fun updateList(newContext:  Context?,newList: List<Featured.FeaturedList>) {

        list.clear()
        list.addAll(newList)
        context = newContext
        notifyDataSetChanged()

    }
}