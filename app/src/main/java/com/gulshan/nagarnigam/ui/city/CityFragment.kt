package com.gulshan.nagarnigam.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gulshan.nagarnigam.databinding.FragmentCityBinding


class CityFragment : Fragment() {
    lateinit var binding: FragmentCityBinding

    companion object {
        fun newInstance() = CityFragment()
    }

    private lateinit var viewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CityViewModel::class.java]
        binding.webViewCity.loadUrl("java/com/gulshan/nagarnigam/assets/KNOW YOUR CITY Chhindwara edited.pdf")
    }

    fun loadPdf(){
        val webview = binding.webViewCity
        val settings: WebSettings = webview.getSettings()
        settings.javaScriptEnabled = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
        settings.builtInZoomControls = true
        webview.setWebChromeClient(WebChromeClient())
        webview.loadUrl(
            "app/src/main/java/com/gulshan/nagarnigam/assets/KNOW YOUR CITY Chhindwara edited.pdf"
        )
    }

}