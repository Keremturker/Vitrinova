package com.mobillium.vitrinova.viewmodel

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
 import com.google.gson.Gson
import com.google.gson.JsonArray
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.model.*
import com.mobillium.vitrinova.model.Collections
import com.mobillium.vitrinova.service.DiscoveryAPIService
import com.mobillium.vitrinova.view.FragmentMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class MainFragmentViewModel : ViewModel() {

    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    val discoverList = MutableLiveData<JsonArray>()
    val featuredList = MutableLiveData<Featured>()
    val productsList = MutableLiveData<Products>()
    val categoriesList = MutableLiveData<Categories>()
    val collectionsList = MutableLiveData<Collections>()
    val editorShopList = MutableLiveData<EditorShop>()
    val newShopList = MutableLiveData<NewShops>()


    private val discoveryAPIService = DiscoveryAPIService()
    private val disposable = CompositeDisposable()


    fun getDataFromAPI() {

        isLoading.value = true

        disposable.add(
            discoveryAPIService.getDiscovery()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonArray>() {
                    override fun onSuccess(t: JsonArray) {
                        isError.value = false
                        isLoading.value = false
                        discoverList.value = t


                        val featuredJsonArray = t[0]
                        val productsJsonArray = t[1]
                        val categoriesJsonArray = t[2]
                        val collectionsJsonArray = t[3]
                        val editorShopJsonArray = t[4]
                        val newShopJsonArray = t[5]

                        featuredList.value =
                            Gson().fromJson(featuredJsonArray, Featured::class.java)


                        productsList.value =
                            Gson().fromJson(productsJsonArray, Products::class.java)

                        categoriesList.value =
                            Gson().fromJson(categoriesJsonArray, Categories::class.java)

                        collectionsList.value =
                            Gson().fromJson(collectionsJsonArray, Collections::class.java)

                        editorShopList.value =
                            Gson().fromJson(editorShopJsonArray, EditorShop::class.java)

                        newShopList.value =
                            Gson().fromJson(newShopJsonArray, NewShops::class.java)


                    }

                    override fun onError(e: Throwable) {

                        isError.value = true
                        isLoading.value = false

                    }


                })

        )

    }


    //ViewPager indicator ekleme işlemi
    fun prepareDots(context: Context, CurrentPosition: Int, size: Int) {

        if (FragmentMain.dotsLayout.childCount > 0) {
            FragmentMain.dotsLayout.removeAllViews()
        }

        var dots = ArrayList<ImageView>()

        for (i in 0 until size) {

            dots.add(ImageView(context))
            if (i == CurrentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot))

            } else {
                dots[i].setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.inactive_dot
                    )
                )

            }

            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(4, 0, 4, 0)






            FragmentMain.dotsLayout.addView(dots[i], layoutParams)

        }


    }



}