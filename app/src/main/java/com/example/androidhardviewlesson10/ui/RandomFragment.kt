package com.example.androidhardviewlesson10.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adapter.RecyclerViewAdapter
import com.example.androidhardviewlesson10.R
import com.example.androidhardviewlesson10.databinding.FragmentPopularBinding
import com.example.androidhardviewlesson10.databinding.FragmentRandomBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.model.ImageAbout
import com.model.MySharedPreference

class RandomFragment : Fragment() {
    lateinit var binding: FragmentRandomBinding
    lateinit var gson: Gson


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentRandomBinding.inflate(inflater, container, false)

        gson = Gson()
        MySharedPreference.init(requireContext())

        val type = object : TypeToken<ArrayList<ImageAbout>>() {}.type

        val newList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.new, type)
        val animalsList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.animals, type)
        val natureList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.nature, type)
        val technologyList =
            gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.technology, type)

        var randomList = ArrayList<ImageAbout>()
        randomList.addAll(newList)
        randomList.addAll(animalsList)
        randomList.addAll(natureList)
        randomList.addAll(technologyList)

        randomList.shuffle()
        var recyclerViewAdapter = RecyclerViewAdapter(randomList)
        binding.recyclerViewRandom.adapter = recyclerViewAdapter
        recyclerViewAdapter.setOnMyItemClickListener {
            val bundleOf = bundleOf("image" to it)
            findNavController().navigate(R.id.imageFragment, bundleOf)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.random_menu, menu)
    }


}