package com.mobillium.vitrinova.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.mobillium.vitrinova.model.*
import com.mobillium.vitrinova.service.DiscoveryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val discoverList = MutableLiveData<JsonArray>()

    private val discoveryAPIService = DiscoveryAPIService()
    private val disposable = CompositeDisposable()


    fun getDataFromAPI() {


        disposable.add(
            discoveryAPIService.getDiscovery()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonArray>() {
                    override fun onSuccess(t: JsonArray) {

                        discoverList.value = t

                        val featuredJsonArray = t[0]
                        val productsJsonArray = t[1]
                        val categoriesJsonArray = t[2]
                        val collectionsJsonArray = t[3]
                        val editorShopJsonArray = t[4]
                        val newShopJsonArray = t[5]

                        val featuredList = Gson().fromJson(featuredJsonArray, Featured::class.java)
                        val productsList = Gson().fromJson(productsJsonArray, Products::class.java)
                        val categoriesList =
                            Gson().fromJson(categoriesJsonArray, Categories::class.java)
                        val collectionsList =
                            Gson().fromJson(collectionsJsonArray, Collections::class.java)
                        val editorShopList =
                            Gson().fromJson(editorShopJsonArray, EditorShop::class.java)
                        val newShopList =
                            Gson().fromJson(newShopJsonArray, NewShops::class.java)


                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }


                })

        )

    }


}