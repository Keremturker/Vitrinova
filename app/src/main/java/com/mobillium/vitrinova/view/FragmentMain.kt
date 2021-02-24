package com.mobillium.vitrinova.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.*
import com.mobillium.vitrinova.util.downloadFromUrl
import com.mobillium.vitrinova.viewmodel.MainFragmentViewModel


class FragmentMain : Fragment() {

    //region Defination
    lateinit var viewModel: MainFragmentViewModel
    private var adapterViewPagerFeatured = ViewPagerFeaturedAdapter(context, arrayListOf())
    private var adapterNewProduct = NewProductAdapter(context, arrayListOf())
    private var adapterCategories = CategoriesAdapter(context, arrayListOf())
    private var adapterCollections = CollectionsAdapter(context, arrayListOf())
    private var adapterViewPagerEditorShop = ViewPagerEditorShopAdapter(context, arrayListOf())
    private var adapterNewShop = NewShopAdapter(context, arrayListOf())
    private lateinit var viewPagerFeatured: ViewPager
    private lateinit var viewPagerEditorShop: ViewPager
    private lateinit var txtNewProductTitle: TextView
    private lateinit var txtCategoriesTitle: TextView
    private lateinit var txtCollectionsTitle: TextView
    private lateinit var txtEditorShopTitle: TextView
    private lateinit var txtNewShopTitle: TextView

    private lateinit var txtNewProductAll: TextView
    private lateinit var txtCollectionsAll: TextView
    private lateinit var txtEditorShopAll: TextView
    private lateinit var txtNewShopAll: TextView

    private lateinit var rvNewProduct: RecyclerView
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvCollections: RecyclerView
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //region View Initialize

        dotsLayout = view.findViewById(R.id.dotsLayout)
        txtNewProductTitle = view.findViewById(R.id.txtNewProductTitle)
        txtCategoriesTitle = view.findViewById(R.id.txtCategoriesTitle)
        txtCollectionsTitle = view.findViewById(R.id.txtCollectionsTitle)
        txtEditorShopTitle = view.findViewById(R.id.txtEditorShopTitle)
        txtNewShopTitle = view.findViewById(R.id.txtNewShopTitle)

        txtNewProductAll = view.findViewById(R.id.txtNewProductAll)
        txtCollectionsAll = view.findViewById(R.id.txtCollectionsAll)
        txtEditorShopAll = view.findViewById(R.id.txtEditorShopAll)
        txtNewShopAll = view.findViewById(R.id.txtNewShopAll)

        rvNewProduct = view.findViewById(R.id.rvNewProduct)
        rvCategories = view.findViewById(R.id.rvCategories)
        rvCollections = view.findViewById(R.id.rvCollections)
        rvNewShop = view.findViewById(R.id.rvNewShop)

        toolbar = view.findViewById(R.id.toolbar)
        edtSearch = view.findViewById(R.id.edtSearch)
        imgVoice = view.findViewById(R.id.imgVoice)

        imgEditorShopBackground = view.findViewById(R.id.imgEditorShopBackground)
        viewPagerFeatured = view.findViewById(R.id.viewPagerFeatured)
        viewPagerEditorShop = view.findViewById(R.id.viewPagerEditorShop)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        pbLoading = view.findViewById(R.id.pbLoaing)


        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        //endregion


        //region Adapter Initialize
        rvNewProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvNewProduct.adapter = adapterNewProduct


        rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = adapterCategories


        rvCollections.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCollections.adapter = adapterCollections

        rvNewShop.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                    requireContext(),
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



        observeLiveData(context)

        imgVoice.setOnClickListener {


            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US")
            startActivityForResult(intent, 1)

        }

        txtNewProductAll.setOnClickListener {

            val productList = viewModel.productsList.value!!.products.toTypedArray()

            val action =
                FragmentMainDirections.actionMainFragmentToFragmentNewProduct(productList)
            Navigation.findNavController(it).navigate(action)

        }

        txtCollectionsAll.setOnClickListener {

            val collectionList = viewModel.collectionsList.value!!.collections.toTypedArray()
            val action =
                FragmentMainDirections.actionMainFragmentToFragmentCollections(collectionList)

            Navigation.findNavController(it).navigate(action)

        }


        txtEditorShopAll.setOnClickListener {

            val editorShopList = viewModel.editorShopList.value!!.shops.toTypedArray()

            val action =
                FragmentMainDirections.actionMainFragmentToFragmentEditorShop(editorShopList)
            Navigation.findNavController(it).navigate(action)

        }

        txtNewShopAll.setOnClickListener {


            val newShopList = viewModel.newShopList.value!!.shops.toTypedArray()

            val action = FragmentMainDirections.actionMainFragmentToFragmentNewShop(newShopList)
            Navigation.findNavController(it).navigate(action)

        }

    }


    private fun observeLiveData(context: Context?) {


        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->

            isLoading?.let {

                if (it) {
                    pbLoading.visibility = View.VISIBLE
                    swipeRefreshLayout.visibility = View.GONE


                } else {

                    pbLoading.visibility = View.GONE
                }

            }

        })

        viewModel.isError.observe(viewLifecycleOwner, { isError ->

            isError?.let {

                if (it) {


                } else {

                }
            }


        })

        viewModel.discoverList.observe(viewLifecycleOwner, {

            it?.let {


                swipeRefreshLayout.visibility = View.VISIBLE

            }


        })


        //featuredList değerinin değiştiğinde ViewPagerAdapter'i günceller
        viewModel.featuredList.observe(viewLifecycleOwner, { featured ->

            featured?.let {

                viewPagerFeatured.offscreenPageLimit = it.featured.size

                adapterViewPagerFeatured.updateList(context, it.featured)
                viewModel.prepareDots(requireContext(), 0, it.featured.size)


            }

        })

        viewModel.productsList.observe(viewLifecycleOwner, { products ->

            products?.let {

                txtNewProductTitle.text = it.title

                adapterNewProduct.updateList(context, it.products)


            }


        })

        viewModel.categoriesList.observe(viewLifecycleOwner, { categories ->

            categories?.let {

                txtCategoriesTitle.text = it.title

                adapterCategories.updateList(context, it.categories)


            }

        })

        viewModel.collectionsList.observe(viewLifecycleOwner, { collections ->

            collections?.let {

                txtCollectionsTitle.text = it.title
                adapterCollections.updateList(context, it.collections)


            }


        })

        viewModel.editorShopList.observe(viewLifecycleOwner, { editorShop ->

            editorShop?.let {

                txtEditorShopTitle.text = it.title

                imgEditorShopBackground.downloadFromUrl(viewModel.editorShopList.value!!.shops[0].cover.url)

                viewPagerEditorShop.offscreenPageLimit = it.shops.size

                adapterViewPagerEditorShop.updateList(context, it.shops)


            }


        })

        viewModel.newShopList.observe(viewLifecycleOwner, { newShop ->


            newShop?.let {

                txtNewShopTitle.text = it.title

                adapterNewShop.updateList(context, it.shops)

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