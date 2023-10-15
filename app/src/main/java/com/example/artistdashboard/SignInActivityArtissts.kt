package com.example.artistdashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.artistdashboard.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivityArtissts : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    private lateinit var artists:HashSet<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_artissts)
        artists = hashSetOf()
        val button=findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.buttonA)
        val email=findViewById<EditText>(R.id.emailA)
        val password=findViewById<EditText>(R.id.passwordA)

        val already=findViewById<TextView>(R.id.alreadyA)
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("UsersandArtists").
        get().
        addOnSuccessListener {
            for(doc in it)
            {
                val x= doc.toObject(User::class.java)
                if(x.genre!=null&&x.genre!="user")
                {
                    artists.add(x.email)
                }
            }
        }

        firebaseAuth= FirebaseAuth.getInstance()
        already.setOnClickListener{
            val intent= Intent(this,SignUpActivityArtists::class.java)
            startActivity(intent)
        }
        button.setOnClickListener{
            val emailn=email.text.toString()
            val passwordn=password.text.toString()


            if(emailn.isNotEmpty() && passwordn.isNotEmpty()) {

                if (artists.contains(emailn)) {

                    firebaseAuth.signInWithEmailAndPassword(emailn, passwordn)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intentmessage = Intent(this, HomeActivityArtists::class.java)
                                startActivity(intentmessage)
//                                val intent = Intent(this, HomeActivity::class.java)
//                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
                else
                    Toast.makeText(this,"Not an Artist!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Please fill all the fields!", Toast.LENGTH_SHORT).show()
            }

        }


    }


}