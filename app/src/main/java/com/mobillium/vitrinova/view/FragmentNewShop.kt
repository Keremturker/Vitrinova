package com.mobillium.vitrinova.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.NewShopAdapter
import com.mobillium.vitrinova.model.Shop


class FragmentNewShop : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_shop, container, false)
    }


    lateinit var rvAllNewShopList: RecyclerView
    lateinit var newShopAdapter: NewShopAdapter
    var newShopList = ArrayList<Shop>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            newShopList.addAll(FragmentNewShopArgs.fromBundle(it).list)

        }

        rvAllNewShopList = view.findViewById(R.id.rvAllNewShopList)
        rvAllNewShopList.layoutManager = LinearLayoutManager(requireContext())
        newShopAdapter = NewShopAdapter(requireContext(), newShopList)

        rvAllNewShopList.adapter = newShopAdapter


    }
}