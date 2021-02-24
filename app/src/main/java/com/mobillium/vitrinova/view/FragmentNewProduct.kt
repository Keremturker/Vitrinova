package com.mobillium.vitrinova.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.NewProductAdapter
import com.mobillium.vitrinova.model.Products

class FragmentNewProduct : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_product, container, false)
    }

    private lateinit var rvAllProductList: RecyclerView
    private lateinit var newProductAdapter: NewProductAdapter
    private var productList = ArrayList<Products.ProductsList>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {


            productList.addAll(FragmentNewProductArgs.fromBundle(it).list)
        }

        rvAllProductList = view.findViewById(R.id.rvAllProductList)
        rvAllProductList.layoutManager = GridLayoutManager(requireContext(), 2)
        newProductAdapter = NewProductAdapter(context, productList)
        rvAllProductList.adapter = newProductAdapter


    }

}