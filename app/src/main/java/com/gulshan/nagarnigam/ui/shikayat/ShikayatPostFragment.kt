package com.gulshan.nagarnigam.ui.shikayat

import SharedPref
import android.R.drawable
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gulshan.nagarnigam.Constants
import com.gulshan.nagarnigam.R
import com.gulshan.nagarnigam.databinding.FragmentShikayatPostBinding
import java.io.InputStream


class ShikayatPostFragment : Fragment() {
    lateinit var binding: FragmentShikayatPostBinding
    val CAMERA_REQUEST = 343
    val GALLERY_REQUEST = 3343
    lateinit var selectedBitmap: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPref.write(Constants.SELECTED_CATEGORY, "Select Category")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentShikayatPostBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOldBitmap(savedInstanceState)
        init()

    }

    fun setOldBitmap(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            try {
                val bitmap = savedInstanceState.getParcelable<Bitmap>("bitmap")
                setImageBitmap(bitmap!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setOldBitmap(savedInstanceState)
    }

    private fun init() {

        binding.ivCamera.imageIcon.setImageDrawable(
            activity?.getDrawable(R.drawable.camera)
        )

        binding.ivGallery.imageIcon.setImageDrawable(
            activity?.getDrawable(R.drawable.image_ic)
        )
        binding.ivCamera.text.text = "Camera"
        binding.ivGallery.text.text = "Gallery"

        binding.ivCamera.container.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }

        binding.ivGallery.container.setOnClickListener {
            val intent = Intent()
//            intent.type = "image/*"
            intent.type = "text/html"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST)
        }

        binding.tlShikayatCategory.editText?.setOnClickListener {
            findNavController().navigate(R.id.action_shikayatPostFragment_to_shikayatFragment)
        }

        binding.submitShikayat.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        val subject = binding.tlShikayatCategory.editText?.text.toString()
        val body = binding.tlDescription.editText?.text.toString()
        val email = "gk135220@gmail.com"

        val bitmap = selectedBitmap

        // Store image in Devise database to send image to mail
        // Store image in Devise database to send image to mail
        val path = Images.Media.insertImage(activity?.contentResolver, bitmap, "title", null)
        val screenshotUri = Uri.parse(path)
        val emailIntent1 = Intent(Intent.ACTION_SEND)
        emailIntent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        emailIntent1.putExtra(Intent.EXTRA_STREAM, screenshotUri)
        emailIntent1.type = "image/png"
        startActivity(Intent.createChooser(emailIntent1, "Send email using"))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == CAMERA_REQUEST) {
                val inputStream: InputStream? =
                    requireContext().contentResolver.openInputStream(data!!.data!!)
                Log.d("gwsg", "onActivityResult: ")
                decodeAndSave(inputStream)
            } else if (requestCode == GALLERY_REQUEST) {
                val inputStream: InputStream? =
                    requireContext().contentResolver.openInputStream(data!!.data!!)
                Log.d("gwsg", "onActivityResult: ")
                decodeAndSave(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun decodeAndSave(inputStream: InputStream?) {
        val image = BitmapFactory.decodeStream(inputStream)
        setImageBitmap(image)
        selectedBitmap = image
    }

    fun setImageBitmap(bitmap: Bitmap) {
        binding.imageUploaded.setImageBitmap(bitmap)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        try {
            if (outState.getParcelable<Bitmap>("bitmap") != null) {
                outState.putParcelable("bitmap", selectedBitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tlShikayatCategory.editText?.setText(
            SharedPref.read(
                Constants.SELECTED_CATEGORY,
                "Select Category"
            )
        )
    }
}