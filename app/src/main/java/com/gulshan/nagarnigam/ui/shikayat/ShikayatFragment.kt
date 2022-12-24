package com.gulshan.nagarnigam.ui.shikayat

import SharedPref
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulshan.nagarnigam.R
import com.gulshan.nagarnigam.databinding.FragmentShikayatBinding
import com.gulshan.nagarnigam.ui.adapters.ShikayatAdapter

class ShikayatFragment : Fragment() {

    lateinit var binding: FragmentShikayatBinding
    lateinit var adapter: ShikayatAdapter

    companion object {
        fun newInstance() = ShikayatFragment()
    }

    private lateinit var viewModel: ShikayatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShikayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShikayatViewModel::class.java)
        init()
    }

    private fun init() {
        activity?.title = "Select Category"
        adapter = ShikayatAdapter()
        adapter.differ.submitList(ShikayatAdapter.shikayatList)
        binding.rvShikayat.adapter = adapter
        binding.rvShikayat.layoutManager = LinearLayoutManager(context)
        binding.button.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:to@gmail.com")))
            findNavController().navigate(R.id.action_shikayatPostFragment_to_shikayatFragment)
        }
        adapter.onItemClickListener = {
            if (!it.isCategory) {
                findNavController().navigateUp()
                SharedPref.write("selectedCategory", it.name)
            }
        }

    }

}