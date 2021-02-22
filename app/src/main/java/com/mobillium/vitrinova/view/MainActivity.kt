package com.mobillium.vitrinova.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.ViewPagerAdapter
import com.mobillium.vitrinova.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var adapter = ViewPagerAdapter(this, arrayListOf())
    private lateinit var viewPager: ViewPager

    companion object {
        lateinit var dotsLayout: LinearLayout
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotsLayout)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                viewModel.prepareDots(
                    this@MainActivity,
                    position,
                    viewModel.featuredList.value?.featured!!.size
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        viewModel.getDataFromAPI()



        observeLiveData()
    }

    private fun observeLiveData() {

        //featuredList değerinin değiştiğinde ViewPagerAdapter'i günceller
        viewModel.featuredList.observe(this, { featured ->

            featured?.let {

                adapter.updateList(it.featured)
                viewModel.prepareDots(this, 0, it.featured.size)


            }

        })

    }


}