package com.example.artistdashboard.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.artistdashboard.ProfileActivityArtists
import com.example.artistdashboard.fragments.HomeFragment
import com.example.artistdashboard.fragments.ProfileFragment

class Adapterfortabs(fm:FragmentManager):FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragmentList : ArrayList<Fragment> = ArrayList()
    var fragmenttitle : ArrayList<String> = ArrayList()



    override fun getCount(): Int {
        return fragmentList.size
    }
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmenttitle[position]
    }

    fun addFragment(fragment: Fragment,title : String){
        fragmentList.add(fragment)
        fragmenttitle.add(title)
    }

}