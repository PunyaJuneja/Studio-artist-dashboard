package com.example.artistdashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.artistdashboard.adapter.Adapterfortabs
import com.example.artistdashboard.fragments.HomeFragment
import com.example.artistdashboard.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivityArtists : AppCompatActivity() {
    lateinit var tablayout:TabLayout
    lateinit var viewpager:ViewPager
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_artists)
        val bottomnav=findViewById(R.id.Abbottomnav) as BottomNavigationView
        bottomnav.menu.findItem(R.id.booking).setChecked(true)
        // bottomnav.selectedItemId=R.id.home
        bottomnav.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.home -> {
                    val i= Intent(this,HomeActivityArtists::class.java)
                    startActivity(i)
                    finish()

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


        tablayout=findViewById(R.id.TabLayout)
        viewpager=findViewById(R.id.ViewPager)


        val fragmentAdapter = Adapterfortabs(supportFragmentManager)
        fragmentAdapter.addFragment(HomeFragment(),"Bookings")
        fragmentAdapter.addFragment(ProfileFragment(),"Account")


        viewpager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewpager)


//        tablayout.addTab(tablayout.newTab().setText("Home"))
//        tablayout.addTab(tablayout.newTab().setText("Profile"))
//        tablayout.tabGravity=TabLayout.GRAVITY_FILL





//        val adapter=Adapterfortabs(this,supportFragmentManager,tablayout.tabCount)
//        viewpager.adapter=adapter
//        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
//        tablayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//               viewpager.currentItem= tab!!.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//
//        })




        auth= Firebase.auth
        val signout=findViewById<Button>(R.id.Artistsignout)
        signout.setOnClickListener{

            auth.signOut()

            val intent= Intent(this,SignUpActivityArtists::class.java)
            startActivity(intent)

        }
    }
}