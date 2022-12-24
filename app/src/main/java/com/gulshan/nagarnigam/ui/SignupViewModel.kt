package com.gulshan.nagarnigam.ui

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.gulshan.nagarnigam.data.model.UserDataObj

class SignupViewModel : ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var database: FirebaseDatabase = Firebase.database
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading


    fun signUp(email: Editable, password: Editable, obj: UserDataObj) {
        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserData(obj)
                    _navigateToHome.value = true
                } else {
                    _toastMessage.value = "Something went wrong"
                }
            }
    }

    private fun saveUserData(obj: UserDataObj) {
        val ref = database.getReference("users")
        ref.child(auth.uid.toString()).setValue(obj)
        _navigateToHome.value = true
    }


}