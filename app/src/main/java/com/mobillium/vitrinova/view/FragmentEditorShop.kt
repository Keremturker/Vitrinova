package com.mobillium.vitrinova.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.vitrinova.R
import com.mobillium.vitrinova.adapter.EditorShopAdapter
import com.mobillium.vitrinova.model.EditorShop


class FragmentEditorShop : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editor_shop, container, false)
    }

    lateinit var rvAllEditorShopList: RecyclerView
    lateinit var editorShopAdapter: EditorShopAdapter

    var editorShopList = ArrayList<EditorShop.Shops>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            editorShopList.addAll(FragmentEditorShopArgs.fromBundle(it).list)

        }


        rvAllEditorShopList = view.findViewById(R.id.rvAllEditorShopList)
        rvAllEditorShopList.layoutManager = LinearLayoutManager(requireContext())
        editorShopAdapter = EditorShopAdapter(requireContext(), editorShopList)
        rvAllEditorShopList.adapter = editorShopAdapter

    }


}