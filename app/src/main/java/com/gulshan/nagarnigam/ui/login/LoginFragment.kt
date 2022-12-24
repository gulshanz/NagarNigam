package com.gulshan.nagarnigam.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.gulshan.nagarnigam.HomeActivity
import com.gulshan.nagarnigam.R
import com.gulshan.nagarnigam.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        authSetup()
        clickListeners()
    }

    private fun clickListeners() {
        binding.login.setOnClickListener {
            login()
        }
        binding.createAccount.setOnClickListener {
            navigateToSignup()
        }
    }

    private fun navigateToSignup() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }


    private fun login() {
        val isValid = validateEmailPassword()
        if (isValid) {
            firebaseLogin()
        } else {
            showError()
        }
    }

    private fun validateEmailPassword(): Boolean {
        return true
    }

    private fun showError() {
        makeToast("Something went wrong")
    }

    private fun firebaseLogin() {
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navigateToHome()
                } else {
                    makeToast(task.exception.toString())
                }
            }
    }

    private fun makeToast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }

    private fun navigateToHome() {
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    private fun authSetup() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToHome()
        }

    }

}