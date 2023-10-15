package com.example.artistdashboard.daos

import com.example.artistdashboard.models.Genre
import com.example.artistdashboard.models.User
import com.example.artistdashboard.models.userinartist
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserDao {
    var str: String = ""
    val db = FirebaseFirestore.getInstance()
    private lateinit var items: ArrayList<User>
    val userscollection = db.collection("UsersandArtists")
    fun adduser(user: User?) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                userscollection.document(user.uid).set(it)
            }
        }
    }
//
//    fun addartistinuser(userid: String, artistinuser: artistinuser) {
//        val userbookings = db.collection("UserBookings").document(userid).collection("Bookings")
//        artistinuser.let {
//            GlobalScope.launch(Dispatchers.IO) {
//                userbookings.document(artistinuser.uid).set(it)
//            }
//        }
//    }

    fun adduserinartist(
        userid: String,
        artistid: String,
        userinartist: userinartist,
        field: String
    ) {

        val artistcollection =
            db.collection("Fields").document(field).collection("Artists").document(artistid)
                .collection("users")
        userinartist.let {
            GlobalScope.launch(Dispatchers.IO) {
                artistcollection.document(userinartist.uid).set(it)
            }
        }

    }

    fun addusertogenre(field: String, genre: Genre?) {
        val fieldcollection = db.collection("Fields").document(field).collection("Artists")
        genre?.let {
            GlobalScope.launch(Dispatchers.IO) {
                fieldcollection.document(genre.uid).set(it)

            }
        }

    }

    suspend fun artistoruser(user: FirebaseUser): Boolean {

        //  var result: Boolean = true
        val doc = db.collection("UsersandArtists").document(user.uid).get().await()
        val obj = doc?.toObject(User::class.java)
        if (obj != null) {
            if (obj.genre == null || obj.genre == "user")
                return true
        }
        return false
    }

    fun declinerequest(artistid: String, user: userinartist, field: String) {
        val artistcollection =
            db.collection("Fields").document(field).collection("Artists").document(artistid)
                .collection("users")
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                artistcollection.document(user.uid).delete()
            }
        }
    }
    fun acceptrequest(artistid:String,user:userinartist,field: String)
    {
        val artistcollection1 =
            db.collection("Fields").document(field).collection("Artists").document(artistid)
                .collection("users")
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                artistcollection1.document(user.uid).delete()
            }
        }
        val artistcollection=db.collection("Fields").document(field).collection("Artists").document(artistid).collection("previous")
        user?.let {
            GlobalScope.launch(Dispatchers.IO){
                artistcollection.document(user.uid).set(it)
            }
        }
    }

}
