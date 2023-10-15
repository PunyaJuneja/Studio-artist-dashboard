package com.example.artistdashboard

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.artistdashboard.adapter.AdapterforArtistHome
import com.example.artistdashboard.adapter.Adapterfortabs
import com.example.artistdashboard.adapter.itemsclicked
import com.example.artistdashboard.daos.UserDao
import com.example.artistdashboard.fragments.HomeFragment
import com.example.artistdashboard.fragments.ImageFragment
import com.example.artistdashboard.fragments.ProfileFragment
import com.example.artistdashboard.fragments.VideoFragment
import com.example.artistdashboard.models.User
import com.example.artistdashboard.models.userinartist
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeActivityArtists : AppCompatActivity() {
    private lateinit var items: ArrayList<userinartist>
    lateinit var firestore: FirebaseFirestore
    lateinit var auth:FirebaseAuth
    lateinit var currentuser:FirebaseUser
    var field:String=""
    lateinit var list:ArrayList<String>
    var id:String=""
    lateinit var adapter: AdapterforArtistHome
    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager
    final val req_code:Int=1000
    lateinit var profile: ImageView
    lateinit var image:ImageView
    //lateinit var Ahrecycler:androidx.recyclerview.widget.RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_artists)
        Log.d(TAG,"home create called")
//        val progressbar =findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.progressbar)
//        progressbar.visibility= View.VISIBLE
        auth = FirebaseAuth.getInstance()
        currentuser= auth.currentUser!!
        firestore=FirebaseFirestore.getInstance()
        items = arrayListOf()
        //Toast.makeText(this,"${currentuser.uid}",Toast.LENGTH_SHORT).show()
        id = currentuser.uid
     //   image = findViewById(R.id.image)
       // val text = findViewById<TextView>(R.id.text)
        val uploadicon=findViewById<ImageView>(R.id.uploadicon)
    //    profile = findViewById(R.id.profilepic)
        uploadicon.setOnClickListener{
            val intent = Intent(this,UploadImage::class.java)
            startActivity(intent)
//            val igallery = Intent(Intent.ACTION_PICK)
//            igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(igallery, req_code)
        }
//
//        val db2=firestore.collection("Fields")
//        val db=firestore.collection("UsersandArtists")
//        GlobalScope.launch {
//
//            val x= db.document(id).get().await().toObject(User::class.java)
//            if (x != null) {
//                field=x.genre
//                val y= db2.document(x.genre).collection("Artists").document(currentuser.uid).collection("users").get().await()
//                y.forEach{
//                    val user=it.toObject(userinartist::class.java)
//                    items.add(user)
//                }
//                val user = y.toObject(userinartist::class.java)
//                if (user != null) {
//                    items.add(user)
//                }
  //          }}
//
//
//            //     Toast.makeText(this,"${items.size}",Toast.LENGTH_SHORT).show()
//        }
       // val Ahrecycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.Ahrecycler)
//        Ahrecycler.layoutManager=LinearLayoutManager(this)
//        adapter = AdapterforArtistHome(items,this)
//        Ahrecycler.adapter=adapter

//        firestore.collection("UsersandArtists").document(currentuser.uid).get().addOnSuccessListener {
//            val x = it.toObject(User::class.java)
//            if (x != null) {
//                field=x.genre
//            }
//
//        }.addOnCompleteListener{
//            firestore.collection("Fields").document(field).collection("Artists").document(currentuser.uid).collection("users").get().addOnSuccessListener {
//                for(doc in it)
//                {
//                    val item=doc.toObject(userinartist::class.java)
//                    items.add(item)
//                }
//            }.addOnCompleteListener{
//                progressbar.visibility=View.GONE
//                adapter.notifyItemChanged(1)
//            }
 //       }



        tablayout=findViewById(R.id.TabLayouthome)
        viewpager=findViewById(R.id.ViewPagerhome)


        val fragmentAdapter = Adapterfortabs(supportFragmentManager)
        fragmentAdapter.addFragment(ImageFragment(),"Images")
        fragmentAdapter.addFragment(VideoFragment(),"Videos")


        viewpager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewpager)


//
//       firestore.collection("UsersandArtists").document(id).get().addOnSuccessListener {document->
//           if(document!=null)
//           {
//               Log.d(TAG,"data = ${document.data}")
//              field = document.data?.get("genre").toString()
//           }
//       }.addOnFailureListener{
//           exception->
//           Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
//       }
        //field=intent.getStringExtra("field").toString()


      //  android.os.Handler().postDelayed(Runnable { //This method will be executed once the timer is over
//            // Start your app main activity
//
//        firestore.collection("Fields").document(field).collection("Artists").document(currentuser.uid).collection("users").get().addOnSuccessListener {
//            for(doc in it)
//            {
//                val user = doc.toObject(userinartist::class.java)
//                items.add(user)
//            }
//            val Ahrecycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.Ahrecycler)
//            Ahrecycler.layoutManager=LinearLayoutManager(this)
//            val adapter = AdapterforArtistHome(items,this)
//            Ahrecycler.adapter=adapter
//       //     Toast.makeText(this,"${items.size}",Toast.LENGTH_SHORT).show()
//        }
//           progressbar.visibility=View.GONE
//            adapter.notifyItemChanged(1)
//        },3000)

 //       android.os.Handler().postDelayed(Runnable {
//            adapter.notifyItemChanged(1)
//        },6000)

//        field=dummy.text.toString()
//        Toast.makeText(this,"${field}",Toast.LENGTH_SHORT).show()
        val bottomnav=findViewById(R.id.Ahbottomnav) as BottomNavigationView
        bottomnav.menu.findItem(R.id.home).setChecked(true)
        // bottomnav.selectedItemId=R.id.home
        bottomnav.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.home -> {
//                    val i= Intent(this,HomeActivity::class.java)
//                    startActivity(i)

                }
                R.id.booking -> {
                    val i= Intent(this,ProfileActivityArtists::class.java)
                    startActivity(i)
                    //    finish()
                    //   return@setOnNavigationItemSelectedListener
                }

            }
            true
        }





    }


    override fun onBackPressed() {
        finish()
        System.exit(0)
    }
//    override fun acceptclicked(uid:String,mail:String) {
//        val userinartist=userinartist(uid,mail)
//        val userdao=UserDao()
//        userdao.acceptrequest(id,userinartist,field)
//        Toast.makeText(this,"Accepted",Toast.LENGTH_SHORT).show()
//        adapter.notifyDataSetChanged()
//        val intent = Intent(this,HomeFragment::class.java)
//        intent.putExtra("field",field)
//        startActivity(intent)

  //  }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== RESULT_OK)
        {
            if(requestCode==req_code)
            {
             //   val uri= data!!.data
                image.setImageURI(data!!.data)
               // onDestroy()
                Log.d(ContentValues.TAG,"zscazsfcasdfcadsfadcf")

            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(ContentValues.TAG,"home Start called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(ContentValues.TAG,"home Stop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(ContentValues.TAG,"home Destroy called")
    }
//
//    override fun userclicked(uid: String,mail:String) {
//        val userinartist =userinartist(uid,mail)
//        val userdao= UserDao()
//        userdao.declinerequest(id,userinartist,field)
//        Toast.makeText(this,"Request Denied",Toast.LENGTH_SHORT).show()
//        adapter.notifyDataSetChanged()
//
//    }
}