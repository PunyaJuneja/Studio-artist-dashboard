package com.example.artistdashboard

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth
        setContentView(R.layout.activity_main)

    }
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        updateUI(user)
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user!=null)
        {
            val intent= Intent(this,HomeActivityArtists::class.java)
            startActivity(intent)

        }
        else
        {
            val intent= Intent(this,SignUpActivityArtists::class.java)
            startActivity(intent)
        }
    }
}