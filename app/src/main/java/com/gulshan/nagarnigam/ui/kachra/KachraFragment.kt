package com.gulshan.nagarnigam.ui.kachra

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gulshan.nagarnigam.R

class KachraFragment : Fragment() {

    companion object {
        fun newInstance() = KachraFragment()
    }

    private lateinit var viewModel: KachraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kachra, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KachraViewModel::class.java)
        // TODO: Use the ViewModel
    }

}