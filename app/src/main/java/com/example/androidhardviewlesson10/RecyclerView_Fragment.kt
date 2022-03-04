package com.example.androidhardviewlesson10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.adapter.RecyclerViewAdapter
import com.example.androidhardviewlesson10.databinding.FragmentRecyclerViewBinding
import com.model.Category
import com.model.ImageAbout

private const val ARG_PARAM1 = "param1"


class RecyclerView_Fragment : Fragment() {
    private var param1: Category? = null
    lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Category
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(
            inflater, container, false
        )
        val recyclerViewAdapter = RecyclerViewAdapter(param1!!.imageList)
        binding.recyclerViewFragment.adapter = recyclerViewAdapter

        recyclerViewAdapter.setOnMyItemClickListener {
            val bundleOf = bundleOf("image" to it)
            findNavController().navigate(R.id.imageFragment, bundleOf)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Category) =
            RecyclerView_Fragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}