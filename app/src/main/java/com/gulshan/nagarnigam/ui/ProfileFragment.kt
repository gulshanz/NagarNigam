package com.gulshan.nagarnigam.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.gulshan.nagarnigam.HomeActivity
import com.gulshan.nagarnigam.MainActivity
import com.gulshan.nagarnigam.data.model.UserDataObj
import com.gulshan.nagarnigam.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    private lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database
        fetchUserData()
        setClickListeners()
    }

    private fun fetchUserData() {

        val usersRef = database.getReference("users").child(auth.uid.toString())
        val userData = usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user: HashMap<String, String> = snapshot.value as HashMap<String, String>
                val obj = UserDataObj(
                    user["fname"]!!, user["lname"]!!, user["mobile"]!!, user["email"]!!
                )
                inflateData(obj)
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progress.visibility = View.GONE
            }

        })

    }

    private fun inflateData(obj: UserDataObj) {
        binding.itEmail.editText?.setText(obj.email)
        binding.itMobile.editText?.setText(obj.mobile)
        binding.itLName.editText?.setText(obj.lName)
        binding.itName.editText?.setText(obj.fName)
        binding.progress.visibility = View.GONE
    }

    private fun setClickListeners() {
        binding.logout.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        activity?.finish()
        startActivity(Intent(activity, MainActivity::class.java))

    }

    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).isNavVisible(true)
    }

}