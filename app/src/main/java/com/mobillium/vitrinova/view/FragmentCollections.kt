package com.mobillium.vitrinova.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.CollectionsAdapter
import com.mobillium.vitrinova.model.Collections


class FragmentCollections : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false)
    }


    lateinit var rvAllCollectionList: RecyclerView
    lateinit var collectionsAdapter: CollectionsAdapter
    var collectionsList = ArrayList<Collections.CollectionsList>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {

            collectionsList.addAll(FragmentCollectionsArgs.fromBundle(it).list)
        }

        rvAllCollectionList = view.findViewById(R.id.rvAllCollectionList)

        rvAllCollectionList.layoutManager = LinearLayoutManager(requireContext())
        collectionsAdapter = CollectionsAdapter(requireContext(), collectionsList)
        rvAllCollectionList.adapter = collectionsAdapter

    }

}