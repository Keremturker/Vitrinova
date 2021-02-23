package com.mobillium.vitrinova.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.ViewPagerAdapter
import com.mobillium.vitrinova.adapter.YeniUrunAdapter
import com.mobillium.vitrinova.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var adapterViewPager = ViewPagerAdapter(this, arrayListOf())
    private var adapterYeniUrun = YeniUrunAdapter(this, arrayListOf())
    private lateinit var viewPager: ViewPager
    private lateinit var txtYeniUrunTitle: TextView
    private lateinit var rvYeniUrun: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    companion object {
        lateinit var dotsLayout: LinearLayout
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotsLayout)
        txtYeniUrunTitle = findViewById(R.id.txtYeniUrunTitle)
        rvYeniUrun = findViewById(R.id.rvYeniUrun)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewPager.adapter = adapterViewPager

        rvYeniUrun.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvYeniUrun.adapter = adapterYeniUrun

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

        swipeRefreshLayout.setOnRefreshListener {


            swipeRefreshLayout.isRefreshing = false


        }



        observeLiveData()
    }

    private fun observeLiveData() {

        //featuredList değerinin değiştiğinde ViewPagerAdapter'i günceller
        viewModel.featuredList.observe(this, { featured ->

            featured?.let {

                adapterViewPager.updateList(it.featured)
                viewModel.prepareDots(this, 0, it.featured.size)


            }

        })

        viewModel.productsList.observe(this, { products ->

            products?.let {

                txtYeniUrunTitle.text = it.title

                adapterYeniUrun.updateList(it.products)


            }


        })

    }


}