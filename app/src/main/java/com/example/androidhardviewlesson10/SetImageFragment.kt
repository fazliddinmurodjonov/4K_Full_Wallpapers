package com.example.androidhardviewlesson10

import android.graphics.Color.RED
import android.graphics.Color.red
import android.graphics.PorterDuff
import android.hardware.camera2.params.RggbChannelVector.RED
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.androidhardviewlesson10.databinding.FragmentSetImageBinding
import com.model.ImageAbout
import com.squareup.picasso.Picasso


class SetImageFragment : Fragment() {
    lateinit var binding: FragmentSetImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetImageBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        val image = arguments?.getSerializable("imageSet") as ImageAbout
        Picasso.get().load(image.image).into(binding.imageView)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
        binding.imageView.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()

    }

}