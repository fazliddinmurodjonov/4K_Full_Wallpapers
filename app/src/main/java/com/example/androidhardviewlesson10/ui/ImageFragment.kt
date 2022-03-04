package com.example.androidhardviewlesson10.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.androidhardviewlesson10.R
import com.example.androidhardviewlesson10.databinding.CustomBottomSheetDialogBinding
import com.example.androidhardviewlesson10.databinding.FragmentImageBinding

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.model.ImageAbout
import com.model.MySharedPreference
import com.squareup.picasso.Picasso


private const val ARG_PARAM1 = "param1"

class ImageFragment : Fragment() {
    lateinit var binding: FragmentImageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MySharedPreference.init(requireContext())
        var gson = Gson()
        binding = FragmentImageBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        val imageShow = arguments?.getSerializable("image") as ImageAbout

        val typeImage = imageShow.imageType!!

        val imageList = loadData(typeImage)

        if (imageShow.imageLiked == true) {
            binding.imageLike.setImageResource(R.drawable.ic_love_pink)
        } else {
            binding.imageLike.setImageResource(R.drawable.ic_love_white)
        }
        Picasso.get().load(imageShow.image).into(binding.image)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.downloadButton.setOnClickListener {
            Toast.makeText(requireContext(), "Download", Toast.LENGTH_SHORT).show()
        }

        binding.shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${imageShow.image}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        binding.setImageButton.setOnClickListener {
            val bundleOf = bundleOf("imageSet" to imageShow)
            findNavController().navigate(R.id.setImageFragment, bundleOf)
        }

        binding.likeButton.setOnClickListener {
            if (imageShow.imageLiked == true) {
                binding.imageLike.setImageResource(R.drawable.ic_love_white)
                imageShow.imageLiked = false
            } else {
                binding.imageLike.setImageResource(R.drawable.ic_love_pink)
                imageShow.imageLiked = true
            }
            for (imageAbout in imageList) {
                if (imageShow.image == imageAbout.image) {
                    imageAbout.imageLiked = imageShow.imageLiked
                    break
                }
            }
            val toJson = gson.toJson(imageList)

            when (typeImage) {
                "animals" -> {
                    MySharedPreference.animals = toJson
                }
                "new" -> {
                    MySharedPreference.new = toJson

                }
                "technology" -> {
                    MySharedPreference.technology = toJson

                }
                "nature" -> {
                    MySharedPreference.nature = toJson

                }
            }

        }

        binding.aboutButton.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)

            val view = CustomBottomSheetDialogBinding.inflate(
                LayoutInflater.from(requireContext()),
                null,
                false
            )
            view.webSite.text = "Website: ${imageShow.imageOfWebSite}"
            view.author.text = "Author: ${imageShow.imageOfAuthor}"
            view.download.text = "Download: ${imageShow.imageDownload.toString()}"
            view.size.text = "Size: ${imageShow.imageSize}"
            dialog.setContentView(view.root)
            binding.backImage.setImageResource(R.drawable.ic_exit)
            binding.aboutImageLayout.visibility = View.INVISIBLE
            binding.shareImageLayout.visibility = View.INVISIBLE
            binding.downloadLayout.visibility = View.INVISIBLE
            binding.setImageLayout.visibility = View.INVISIBLE
            binding.starLayout.visibility = View.INVISIBLE
            binding.likeLayout.visibility = View.INVISIBLE

            dialog.setOnDismissListener {
                binding.aboutImageLayout.visibility = View.VISIBLE
                binding.backImage.setImageResource(R.drawable.ic_back)
                binding.aboutImageLayout.visibility = View.VISIBLE
                binding.shareImageLayout.visibility = View.VISIBLE
                binding.downloadLayout.visibility = View.VISIBLE
                binding.setImageLayout.visibility = View.VISIBLE
                binding.starLayout.visibility = View.VISIBLE
                binding.likeLayout.visibility = View.VISIBLE
            }
            binding.backButtonLayout.setOnClickListener {
                binding.aboutImageLayout.visibility = View.VISIBLE
                binding.backImage.setImageResource(R.drawable.ic_back)
                binding.aboutImageLayout.visibility = View.VISIBLE
                binding.shareImageLayout.visibility = View.VISIBLE
                binding.downloadLayout.visibility = View.VISIBLE
                binding.setImageLayout.visibility = View.VISIBLE
                binding.starLayout.visibility = View.VISIBLE
                binding.likeLayout.visibility = View.VISIBLE
            }

            dialog.show()

        }

        return binding.root
    }

    private fun loadData(typeImage: String): ArrayList<ImageAbout> {
        var gson = Gson()
        val type = object : TypeToken<ArrayList<ImageAbout>>() {}.type
        var imageList = ArrayList<ImageAbout>()
        when (typeImage) {
            "animals" -> {
                val fromJson =
                    gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.animals, type)
                imageList = fromJson
            }
            "new" -> {
                val fromJson = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.new, type)
                imageList = fromJson
            }
            "technology" -> {
                val fromJson =
                    gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.technology, type)
                imageList = fromJson
            }
            "nature" -> {
                val fromJson = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.nature, type)
                imageList = fromJson
            }
        }
        return imageList
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }


}