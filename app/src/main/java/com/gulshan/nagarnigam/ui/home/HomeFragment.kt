package com.gulshan.nagarnigam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gulshan.nagarnigam.HomeActivity
import com.gulshan.nagarnigam.ui.adapters.MenuAdapter
import com.gulshan.nagarnigam.R
import com.gulshan.nagarnigam.databinding.FragmentHomeFragmentBinding
import com.gulshan.nagarnigam.ui.adapters.HomeDpAdapter
import java.util.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeFragmentBinding
    private lateinit var adapter: MenuAdapter
    val imageId =
        arrayOf(
            R.drawable.home_image_0,
            R.drawable.home_image_1,
            R.drawable.home_image_2
        )
    val imagesName = arrayOf("image1", "image2", "image3", "image4")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        adapter = MenuAdapter()
        adapter.differ.submitList(MenuAdapter.menuList)
        adapter.onItemClickListener = { item ->
            when (item.name) {
                "shikayat" -> {
                    findNavController().navigate(R.id.action_homeFragment2_to_shikayatPostFragment)
                    makeNavVisible(false)
                }
                "city" -> {
                    findNavController().navigate(R.id.action_homeFragment2_to_cityFragment)
                    makeNavVisible(false)
                }
                "kachra" -> {
                    findNavController().navigate(R.id.action_homeFragment2_to_kachraFragment)
                    makeNavVisible(false)
                }
            }
        }
        binding.rvMenu.adapter = adapter
        binding.rvMenu.layoutManager = GridLayoutManager(context, 3)
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPager = binding.ivHome
        val adapter: PagerAdapter = HomeDpAdapter(imageId, requireActivity())
        viewPager.adapter = adapter

        setTimer(viewPager, adapter)
    }

    private fun setTimer(viewPager: ViewPager, adapter: PagerAdapter) {
        var currentPage = 0
        val timer: Timer
        val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed

        val PERIOD_MS: Long = 3000

        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                viewPager.post {
                    val itemObj = (viewPager.currentItem + 1) % imageId.size
//                    viewPager.currentItem = (viewPager.currentItem + 1) % imageId.size
                    viewPager.setCurrentItem(itemObj, true)
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 5000, 5000)
    }

    override fun onResume() {
        super.onResume()
        makeNavVisible(true)
    }

    override fun onPause() {
        super.onPause()
    }

    fun makeNavVisible(boolean: Boolean) {
        (activity as HomeActivity).isNavVisible(boolean)
    }

}