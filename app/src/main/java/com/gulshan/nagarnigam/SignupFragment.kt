package com.gulshan.nagarnigam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.gulshan.nagarnigam.data.model.UserDataObj
import com.gulshan.nagarnigam.databinding.FragmentSignupBinding
import com.gulshan.nagarnigam.ui.SignupViewModel
import com.gulshan.nagarnigam.ui.home.HomeFragmentViewModel

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    private lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        auth = FirebaseAuth.getInstance()
        database = Firebase.database
        binding.btnSignup.setOnClickListener {
            signup()
        }
        addObservers()
    }

    private fun addObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.navigateToHome.observe(this) {
            if (it) {
                navigateToHome()
            }
        }

        viewModel.toastMessage.observe(this) {
            makeToast(it)
        }
    }

    private fun signup() {
        val email = binding.etEmail.editText?.text
        val fName = binding.etFname.editText?.text
        val lName = binding.etLanme.editText?.text
        val mobile = binding.etMobile.editText?.text
        val password = binding.etPassword.editText?.text
        val confirmPass = binding.etConfirmPassword.editText?.text
        if (email.isNullOrBlank()) {
            makeToast("Please enter email")
            return
        }
        if (fName.isNullOrBlank()) {
            makeToast("Please enter first Name")
            return
        }
        if (mobile.isNullOrBlank()) {
            makeToast("Please enter last Name")
            return
        }
        if (password.isNullOrBlank()) {
            makeToast("Please enter password")
            return
        }
        if (confirmPass.isNullOrBlank()) {
            makeToast("Please enter confirm password")
            return
        }
        if (lName.isNullOrBlank()) {
            makeToast("Please enter last Name")
            return
        }
        if (password.toString() != confirmPass.toString()) {
            makeToast("Password and confirm password must be same")
        }
        val obj = UserDataObj(
            fName.toString(), lName.toString(), mobile.toString(), email.toString()
        )
        viewModel.signUp(email, password, obj)
    }

    private fun navigateToHome() {
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    private fun makeToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

}