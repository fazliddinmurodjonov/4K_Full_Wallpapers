package com.example.androidhardviewlesson10.ui

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adapter.CategoryAdapter
import com.adapter.RecyclerViewAdapter
import com.example.androidhardviewlesson10.R
import com.example.androidhardviewlesson10.databinding.FragmentPopularBinding
import com.example.androidhardviewlesson10.databinding.ItemTabLayoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.model.Category
import com.model.ImageAbout
import com.model.MySharedPreference


class PopularFragment : Fragment() {

    lateinit var binding: FragmentPopularBinding
    lateinit var gson: Gson


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gson = Gson()
        MySharedPreference.init(requireContext())
        binding = FragmentPopularBinding.inflate(inflater, container, false)

        val type = object : TypeToken<ArrayList<ImageAbout>>() {}.type

        val newList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.new, type)
        val animalsList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.animals, type)
        val natureList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.nature, type)
        val technologyList =
            gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.technology, type)

        var popularList = ArrayList<ImageAbout>()
        popularList.addAll(newList)
        popularList.addAll(animalsList)
        popularList.addAll(natureList)
        popularList.addAll(technologyList)

        var recyclerViewAdapter = RecyclerViewAdapter(popularList)
        binding.recyclerViewPopular.adapter = recyclerViewAdapter
        (activity as AppCompatActivity).supportActionBar!!.show()
        recyclerViewAdapter.setOnMyItemClickListener {
            val bundleOf = bundleOf("image" to it)
            findNavController().navigate(R.id.imageFragment, bundleOf)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }


}