package com.gulshan.nagarnigam.ui.shikayat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gulshan.nagarnigam.R

class ShikayatFragment : Fragment() {

    companion object {
        fun newInstance() = ShikayatFragment()
    }

    private lateinit var viewModel: ShikayatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shikayat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShikayatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}