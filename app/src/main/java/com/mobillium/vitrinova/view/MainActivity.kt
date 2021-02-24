package com.mobillium.vitrinova.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.*
import com.mobillium.vitrinova.util.downloadFromUrl
import com.mobillium.vitrinova.viewmodel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {

    //region Defination
    lateinit var viewModel: MainViewModel
    private var adapterViewPagerFeatured = ViewPagerFeaturedAdapter(this, arrayListOf())
    private var adapterYeniUrun = YeniUrunAdapter(this, arrayListOf())
    private var adapterKategoriler = KategorilerAdapter(this, arrayListOf())
    private var adapterKoleksiyon = KoleksiyonAdapter(this, arrayListOf())
    private var adapterViewPagerEditorShop = ViewPagerEditorShopAdapter(this, arrayListOf())
    private var adapterNewShop = NewShopAdapter(this, arrayListOf())
    private lateinit var viewPagerFeatured: ViewPager
    private lateinit var viewPagerEditorShop: ViewPager
    private lateinit var txtYeniUrunTitle: TextView
    private lateinit var txtKategoriTitle: TextView
    private lateinit var txtKoleksiyonTitle: TextView
    private lateinit var txtEditorShopTitle: TextView
    private lateinit var txtNewShopTitle: TextView
    private lateinit var rvYeniUrun: RecyclerView
    private lateinit var rvKategoriler: RecyclerView
    private lateinit var rvKoleksiyon: RecyclerView
    private lateinit var rvNewShop: RecyclerView
    private lateinit var imgEditorShopBackground: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var imgVoice: ImageView

    private lateinit var edtSearch: EditText
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var pbLoading: ProgressBar

    companion object {
        lateinit var dotsLayout: LinearLayout
    }

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region View Initialize
        viewPagerFeatured = findViewById(R.id.viewPagerFeatured)
        dotsLayout = findViewById(R.id.dotsLayout)
        txtYeniUrunTitle = findViewById(R.id.txtYeniUrunTitle)
        txtKategoriTitle = findViewById(R.id.txtKategoriTitle)
        txtKoleksiyonTitle = findViewById(R.id.txtKoleksiyonTitle)
        txtEditorShopTitle = findViewById(R.id.txtEditorShopTitle)
        txtNewShopTitle = findViewById(R.id.txtNewShopTitle)
        rvYeniUrun = findViewById(R.id.rvYeniUrun)
        rvKategoriler = findViewById(R.id.rvKategori)
        rvKoleksiyon = findViewById(R.id.rvKoleksiyon)
        rvNewShop = findViewById(R.id.rvNewShop)
        toolbar = findViewById(R.id.toolbar)
        edtSearch = findViewById(R.id.edtSearch)
        imgVoice = findViewById(R.id.imgVoice)
        imgEditorShopBackground = findViewById(R.id.imgEditorShopBackground)
        viewPagerEditorShop = findViewById(R.id.viewPagerEditorShop)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        pbLoading = findViewById(R.id.pbLoaing)


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

        rvNewShop.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvNewShop.adapter = adapterNewShop


        val snapHelperNewShop: SnapHelper = PagerSnapHelper()
        snapHelperNewShop.attachToRecyclerView(rvNewShop)



        viewPagerFeatured.adapter = adapterViewPagerFeatured
        viewPagerEditorShop.adapter = adapterViewPagerEditorShop

        viewPagerEditorShop.currentItem = 0
        viewPagerEditorShop.setPadding(50, 0, 50, 0)
        viewPagerEditorShop.pageMargin = 30
        viewPagerEditorShop.clipToPadding = false

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

        viewPagerEditorShop.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {


            }

            override fun onPageSelected(position: Int) {


                imgEditorShopBackground.downloadFromUrl(viewModel.editorShopList.value!!.shops[position].cover.url)

            }

            override fun onPageScrollStateChanged(state: Int) {
            }


        })



        viewModel.getDataFromAPI()

        swipeRefreshLayout.setOnRefreshListener {

            edtSearch.text.clear()
            viewModel.getDataFromAPI()


            swipeRefreshLayout.isRefreshing = false


        }



        observeLiveData()

        imgVoice.setOnClickListener {


            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US")
            startActivityForResult(intent, 1)

        }


    }

    private fun observeLiveData() {


        viewModel.isLoading.observe(this, { isLoading ->

            isLoading?.let {

                if (it) {
                    pbLoading.visibility = View.VISIBLE
                    swipeRefreshLayout.visibility = View.GONE


                } else {

                    pbLoading.visibility = View.GONE
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

                viewPagerFeatured.offscreenPageLimit = it.featured.size

                adapterViewPagerFeatured.updateList(it.featured)
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

                imgEditorShopBackground.downloadFromUrl(viewModel.editorShopList.value!!.shops[0].cover.url)

                viewPagerEditorShop.offscreenPageLimit = it.shops.size

                adapterViewPagerEditorShop.updateList(it.shops)


            }


        })

        viewModel.newShopList.observe(this, { newShop ->


            newShop?.let {

                txtNewShopTitle.text = it.title

                adapterNewShop.updateList(it.shops)

            }

        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK && null != data) {

                edtSearch.setText(
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0).toString()
                )


            }

        }
    }


}