package com.mobillium.vitrinova.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.*
import com.mobillium.vitrinova.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    //region Defination
    lateinit var viewModel: MainViewModel
    private var adapterViewPager = ViewPagerAdapter(this, arrayListOf())
    private var adapterYeniUrun = YeniUrunAdapter(this, arrayListOf())
    private var adapterKategoriler = KategorilerAdapter(this, arrayListOf())
    private var adapterKoleksiyon = KoleksiyonAdapter(this, arrayListOf())
    private var adapterEditorShop = EditorShopAdapter(this, arrayListOf())
    private lateinit var viewPagerFeatured: ViewPager
    private lateinit var txtYeniUrunTitle: TextView
    private lateinit var txtKategoriTitle: TextView
    private lateinit var txtKoleksiyonTitle: TextView
    private lateinit var txtEditorShopTitle: TextView
    private lateinit var rvYeniUrun: RecyclerView
    private lateinit var rvKategoriler: RecyclerView
    private lateinit var rvKoleksiyon: RecyclerView
    private lateinit var rvEditorShop: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var pbLoaing: ProgressBar
    private lateinit var clEditorShop: ConstraintLayout

    companion object {
        lateinit var dotsLayout: LinearLayout
    }

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region View Initialize
        viewPagerFeatured = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotsLayout)
        txtYeniUrunTitle = findViewById(R.id.txtYeniUrunTitle)
        txtKategoriTitle = findViewById(R.id.txtKategoriTitle)
        txtKoleksiyonTitle = findViewById(R.id.txtKoleksiyonTitle)
        txtEditorShopTitle = findViewById(R.id.txtEditorShopTitle)
        rvYeniUrun = findViewById(R.id.rvYeniUrun)
        rvKategoriler = findViewById(R.id.rvKategori)
        rvKoleksiyon = findViewById(R.id.rvKoleksiyon)
        rvEditorShop = findViewById(R.id.rvEditorShop)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        clEditorShop = findViewById(R.id.clEditorShop)
        pbLoaing = findViewById(R.id.pbLoaing)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //endregion


        //region Adapter Initialize
        rvYeniUrun.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvYeniUrun.adapter = adapterYeniUrun

        rvKategoriler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvKategoriler.adapter = adapterKategoriler


        rvKoleksiyon.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvKoleksiyon.adapter = adapterKoleksiyon

        rvEditorShop.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvEditorShop.adapter = adapterEditorShop

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvEditorShop)




        viewPagerFeatured.adapter = adapterViewPager
        rvEditorShop.adapter = adapterEditorShop

        //endregion


        viewPagerFeatured.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

            viewModel.getDataFromAPI()

            swipeRefreshLayout.isRefreshing = false


        }



        observeLiveData()
    }

    private fun observeLiveData() {


        viewModel.isLoading.observe(this, { isLoading ->

            isLoading?.let {

                if (it) {
                    pbLoaing.visibility = View.VISIBLE
                    swipeRefreshLayout.visibility = View.GONE


                } else {
                    pbLoaing.visibility = View.GONE
                }

            }

        })

        viewModel.isError.observe(this, { isError ->

            isError?.let {

                if (it) {


                } else {

                }
            }


        })

        viewModel.discoverList.observe(this, {

            it?.let {


                swipeRefreshLayout.visibility = View.VISIBLE

            }


        })


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

        viewModel.categoriesList.observe(this, { categories ->

            categories?.let {

                txtKategoriTitle.text = it.title

                adapterKategoriler.updateList(it.categories)


            }

        })

        viewModel.collectionsList.observe(this, { collections ->

            collections?.let {

                txtKoleksiyonTitle.text = it.title
                adapterKoleksiyon.updateList(it.collections)


            }


        })

        viewModel.editorShopList.observe(this, { editorShop ->

            editorShop?.let {

                txtEditorShopTitle.text = it.title
                Log.d("positions", "" + it.shops.size)

                adapterEditorShop.updateList(it.shops)


            }


        })

    }


}