package com.example.androidhardviewlesson10.ui

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.adapter.CategoryAdapter
import com.example.androidhardviewlesson10.R

import com.example.androidhardviewlesson10.databinding.FragmentHomeBinding
import com.example.androidhardviewlesson10.databinding.ItemTabLayoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.model.Category
import com.model.ImageAbout
import com.model.MySharedPreference

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var gson: Gson
    lateinit var allList: ArrayList<ImageAbout>
    lateinit var categoryList: ArrayList<Category>
    var categoryAdapter: CategoryAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MySharedPreference.init(requireContext())
        gson = Gson()
        loadImages()
        categoryList = ArrayList()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        allList = ArrayList()

        val type = object : TypeToken<ArrayList<ImageAbout>>() {}.type
        val gsonNewList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.new, type)
        val gsonAnimalsList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.animals, type)
        val gsonTechnologyList =
            gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.technology, type)
        val gsonNatureList = gson.fromJson<ArrayList<ImageAbout>>(MySharedPreference.nature, type)

        allList.addAll(gsonNewList)
        allList.addAll(gsonAnimalsList)
        allList.addAll(gsonTechnologyList)
        allList.addAll(gsonNatureList)


        setHasOptionsMenu(true)
        categoryList.add(Category("All", allList))
        categoryList.add(Category("New", gsonNewList))
        categoryList.add(Category("Animals", gsonAnimalsList))
        categoryList.add(Category("Technology", gsonTechnologyList))
        categoryList.add(Category("Nature", gsonNatureList))


        categoryAdapter = CategoryAdapter(categoryList, requireActivity())

        binding.viewPager.adapter = categoryAdapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabCustomView =
                    ItemTabLayoutBinding.inflate(LayoutInflater.from(requireContext()), null, false)
                tab?.customView = null
                tabCustomView.tabLayoutText.text = categoryList[tab!!.position].title
                tabCustomView.tabLayoutText.setTextColor(Color.WHITE)

                tabCustomView.tabLayoutIndicator.visibility = View.VISIBLE
                tab?.customView = tabCustomView.root

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabCustomView =
                    ItemTabLayoutBinding.inflate(LayoutInflater.from(requireContext()), null, false)
                tab?.customView = null
                tabCustomView.tabLayoutText.text = categoryList[tab!!.position].title
                tabCustomView.tabLayoutIndicator.visibility = View.INVISIBLE
                tab?.customView = tabCustomView.root
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        setTab()


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        loadImages()
        categoryAdapter = CategoryAdapter(categoryList, requireActivity())
        binding.viewPager.adapter = categoryAdapter

    }

    private fun loadImages() {
        var newList: ArrayList<ImageAbout>
        var animalsList: ArrayList<ImageAbout>
        var technologyList: ArrayList<ImageAbout>
        var natureList: ArrayList<ImageAbout>
        if (MySharedPreference.animals == null) {
            animalsList = ArrayList()
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/146083/pexels-photo-146083.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&amp;dpr=1",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/4588065/pexels-photo-4588065.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3608263/pexels-photo-3608263.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3396657/pexels-photo-3396657.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3651618/pexels-photo-3651618.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/87403/cheetah-leopard-animal-big-87403.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10183618/pexels-photo-10183618.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10183617/pexels-photo-10183617.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/4587979/pexels-photo-4587979.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2402927/pexels-photo-2402927.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/4934920/pexels-photo-4934920.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/62289/yemen-chameleon-chamaeleo-calyptratus-chameleon-reptile-62289.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10194701/pexels-photo-10194701.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.unsplash.com/photo-1529778873920-4da4926a72c2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=436&q=80",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.unsplash.com/photo-1484406566174-9da000fda645?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=389&q=80",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.unsplash.com/photo-1629812456605-4a044aa38fbc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.unsplash.com/photo-1516934024742-b461fba47600?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.unsplash.com/photo-1623951578056-5082d34a9859?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=385&q=80",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )

            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2123375/pexels-photo-2123375.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )

            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2313396/pexels-photo-2313396.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "animals",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            animalsList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2123375/pexels-photo-2123375.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "animals",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            val animals = gson.toJson(animalsList)
            MySharedPreference.animals = animals
        }
        if (MySharedPreference.new == null) {
            newList = ArrayList()
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10132782/pexels-photo-10132782.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10139538/pexels-photo-10139538.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10156706/pexels-photo-10156706.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10152077/pexels-photo-10152077.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9023661/pexels-photo-9023661.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/7404941/pexels-photo-7404941.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/5824627/pexels-photo-5824627.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9038606/pexels-photo-9038606.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9556451/pexels-photo-9556451.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10050591/pexels-photo-10050591.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9866757/pexels-photo-9866757.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9406935/pexels-photo-9406935.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9866801/pexels-photo-9866801.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/5797938/pexels-photo-5797938.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/8917087/pexels-photo-8917087.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/8551174/pexels-photo-8551174.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10012578/pexels-photo-10012578.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9866829/pexels-photo-9866829.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9827400/pexels-photo-9827400.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            newList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3689532/pexels-photo-3689532.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "new",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            val toJson = gson.toJson(newList)
            MySharedPreference.new = toJson
        }
        if (MySharedPreference.technology == null) {
            technologyList = ArrayList()
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2582937/pexels-photo-2582937.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3913025/pexels-photo-3913025.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3568520/pexels-photo-3568520.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3861964/pexels-photo-3861964.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2603464/pexels-photo-2603464.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2280547/pexels-photo-2280547.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3201478/pexels-photo-3201478.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3912992/pexels-photo-3912992.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2047905/pexels-photo-2047905.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/5082237/pexels-photo-5082237.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )

            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/163143/sackcloth-sackcloth-textured-laptop-ipad-163143.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2528116/pexels-photo-2528116.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/699459/pexels-photo-699459.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10147358/pexels-photo-10147358.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            technologyList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/572056/pexels-photo-572056.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "technology",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            val toJson = gson.toJson(technologyList)
            MySharedPreference.technology = toJson
        }
        if (MySharedPreference.nature == null) {
            natureList = ArrayList()
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3225517/pexels-photo-3225517.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/3225524/pexels-photo-3225524.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/2770933/pexels-photo-2770933.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/4319752/pexels-photo-4319752.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/4215113/pexels-photo-4215113.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/1402850/pexels-photo-1402850.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/8961218/pexels-photo-8961218.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/6381297/pexels-photo-6381297.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10202925/pexels-photo-10202925.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/6945375/pexels-photo-6945375.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    "nature",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/10183919/pexels-photo-10183919.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/1486974/pexels-photo-1486974.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/9809192/pexels-photo-9809192.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/1666021/pexels-photo-1666021.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    false,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            natureList.add(
                ImageAbout(
                    "https://images.pexels.com/photos/132428/pexels-photo-132428.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                    "nature",
                    true,
                    false,
                    "www.pexels.com",
                    "John Musk",
                    454162,
                    "2.4 MB 2300x4000"
                )
            )
            val toJson = gson.toJson(natureList)
            MySharedPreference.nature = toJson
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }

    private fun setTab() {
        val tabCount = binding.tabLayout.tabCount

        for (i in 0 until tabCount) {
            val tabCustomView =
                ItemTabLayoutBinding.inflate(LayoutInflater.from(requireContext()), null, false)
            val tabAt = binding.tabLayout.getTabAt(i)
            tabAt!!.customView = tabCustomView.root
            tabCustomView.tabLayoutText.text = categoryList[i].title
            if (i == 0) {
                tabCustomView.tabLayoutText.setTextColor(Color.WHITE)
                tabCustomView.tabLayoutIndicator.visibility = View.VISIBLE
            } else {
                tabCustomView.tabLayoutIndicator.visibility = View.INVISIBLE
            }
        }
    }


}