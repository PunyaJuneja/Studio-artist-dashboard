package com.example.artistdashboard.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistdashboard.R
import com.example.artistdashboard.adapter.AdapterforArtistHome
import com.example.artistdashboard.adapter.itemsclicked
import com.example.artistdashboard.models.User
import com.example.artistdashboard.models.userinartist
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment(), itemsclicked {
    lateinit var firestore: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    lateinit var currentuser: FirebaseUser
    private lateinit var items: ArrayList<userinartist>
    private lateinit var itemsreq: ArrayList<userinartist>
    lateinit var recycler:androidx.recyclerview.widget.RecyclerView
    lateinit var adapter:AdapterforArtistHome
    var field:String=""
   lateinit var  progressbar:com.airbnb.lottie.LottieAnimationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
       // field = requireActivity().intent.getStringExtra("field").toString()
         progressbar =view.findViewById(R.id.progresshomef)
        progressbar.visibility= View.VISIBLE
        val menu=view.findViewById<ImageView>(R.id.menubar)
        menu.setOnClickListener{
            showPopup(view)
        }
        items= arrayListOf()
         recycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerhomef)
        recycler.layoutManager= LinearLayoutManager(context)
        adapter = AdapterforArtistHome(items,this)
        recycler.adapter=adapter
        getdata()

    }
    fun getdata()
    {

        items.clear()
        auth = FirebaseAuth.getInstance()
        currentuser= auth.currentUser!!
        firestore=FirebaseFirestore.getInstance()

        firestore.collection("UsersandArtists").document(currentuser.uid).get().addOnSuccessListener {
            val x = it.toObject(User::class.java)
            if (x != null) {
                field=x.genre
            }

        }.addOnCompleteListener{
        firestore.collection("Fields").document(field).collection("Artists").document(currentuser.uid).collection("previous").get().addOnSuccessListener {

            for(doc in it)
            {

                val item=doc.toObject(userinartist::class.java)
                items.add(item)
            }
        }.addOnCompleteListener{
            progressbar.visibility=View.GONE

            adapter.notifyDataSetChanged()
        }
    }
    }
    fun getdatarequests()
    {

        items.clear()
        auth = FirebaseAuth.getInstance()
        currentuser= auth.currentUser!!
        firestore=FirebaseFirestore.getInstance()

        firestore.collection("UsersandArtists").document(currentuser.uid).get().addOnSuccessListener {
            val x = it.toObject(User::class.java)
            if (x != null) {
                field=x.genre
            }

        }.addOnCompleteListener{
            firestore.collection("Fields").document(field).collection("Artists").document(currentuser.uid).collection("users").get().addOnSuccessListener {

                for(doc in it)
                {

                    val item=doc.toObject(userinartist::class.java)
                    items.add(item)
                }
            }.addOnCompleteListener{
                progressbar.visibility=View.GONE
               adapter.notifyDataSetChanged()

            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

       inflater.inflate(R.menu.bottomnavigationmenu,menu)
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
    fun showPopup(v : View){
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.bottomnavigationmenu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.requests-> {
                    getdatarequests()
                    Toast.makeText(activity,"requwsts",Toast.LENGTH_LONG).show()
                }
                R.id.confirmed-> {
                    getdata()
                    Toast.makeText(activity,"confirmed",Toast.LENGTH_LONG).show()
                }
            }
            true
        }
        popup.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==R.id.confirmed)
        {
        Toast.makeText(activity,"confirmed",Toast.LENGTH_LONG).show()
        }
        if(id==R.id.requests)
        {
            Toast.makeText(activity,"requwsts",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun userclicked(uid: String, mail: String) {

    }

    override fun acceptclicked(uid: String, mail: String) {

    }


}