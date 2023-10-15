package com.example.artistdashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.artistdashboard.daos.UserDao
import com.example.artistdashboard.models.Genre
import com.example.artistdashboard.models.User
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivityArtists : AppCompatActivity() {
    private val rc_sign_in:Int=123
    private val TAG = "SignInActivity Tag"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    val fields = arrayOf<String>("Chef","DJ","Dance Performance","Live Music","Poetry","Rap session")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_artists)
        auth = Firebase.auth
        firebaseAuth=FirebaseAuth.getInstance()
        val button=findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.buttonA)
        val email=findViewById<EditText>(R.id.emailA)
        val password=findViewById<EditText>(R.id.passwordA)
        val confirm=findViewById<EditText>(R.id.confirmA)
        val already=findViewById<TextView>(R.id.alreadyA)
        val username=findViewById<EditText>(R.id.usernameA)
        val field =findViewById<AutoCompleteTextView>(R.id.field)
        val adapter: ArrayAdapter<*>
        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,fields)
        field.setAdapter(adapter)



        already.setOnClickListener{
            val intent= Intent(this,SignInActivityArtissts::class.java)
            startActivity(intent)
        }
        button.setOnClickListener{
            val emailn=email.text.toString()
            val passwordn=password.text.toString()
            val confirmn=confirm.text.toString()
            val usernamen=username.text.toString()
            val genre = field.text.toString()
            if(emailn.isNotEmpty() && passwordn.isNotEmpty() && confirmn.isNotEmpty())
            {
                if(passwordn.equals(confirmn))
                {

                    firebaseAuth.createUserWithEmailAndPassword(emailn,passwordn).addOnCompleteListener{
                        if(it.isSuccessful)
                        {

                            val firebaseUser=firebaseAuth.currentUser
                            val user= firebaseUser?.email?.let { User(firebaseUser.uid, it,genre) }
                            val userDao= UserDao()
                            userDao.adduser(user)
                            val genreobject= Genre(usernamen, firebaseUser?.uid.toString())
                            userDao.addusertogenre(genre,genreobject)

                            val intent= Intent(this,SignInActivityArtissts::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Please fill all the fields!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onBackPressed() {
        finish()
        System.exit(0)
    }

}