package com.example.androidhardviewlesson10.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.adapter.RecyclerViewAdapter
import com.example.androidhardviewlesson10.R
import com.example.androidhardviewlesson10.databinding.FragmentLikedBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.model.ImageAbout
import com.model.MySharedPreference


class LikedFragment : Fragment() {
    lateinit var binding: FragmentLikedBinding
    lateinit var gson: Gson
    lateinit var likedImageList: ArrayList<ImageAbout>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentLikedBinding.inflate(inflater, container, false)
        MySharedPreference.init(requireContext())
        gson = Gson()
        likedImageList = ArrayList()
        var type = object : TypeToken<ArrayList<ImageAbout>>() {}.type
        val animalsList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.animals, type)
        val newList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.new, type)
        val technologyList =
            gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.technology, type)
        val natureList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.nature, type)

        for (imageAbout in animalsList) {
            if (imageAbout.imageLiked == true) {
                likedImageList.add(imageAbout)
            }
        }
        for (imageAbout in newList) {
            if (imageAbout.imageLiked == true) {
                likedImageList.add(imageAbout)
            }
        }
        for (imageAbout in technologyList) {
            if (imageAbout.imageLiked == true) {
                likedImageList.add(imageAbout)
            }
        }
        for (imageAbout in natureList) {
            if (imageAbout.imageLiked == true) {
                likedImageList.add(imageAbout)
            }
        }
        var recyclerViewAdapter = RecyclerViewAdapter(likedImageList)
        binding.recyclerViewLiked.adapter = recyclerViewAdapter

        recyclerViewAdapter.setOnMyItemClickListener {
            val bundleOf = bundleOf("image" to it)
            findNavController().navigate(R.id.imageFragment, bundleOf)

        }

        return binding.root
    }


}