package com.example.artistdashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.artistdashboard.R
import com.example.artistdashboard.fragments.HomeFragment
import com.example.artistdashboard.models.userinartist

class AdapterforArtistHome(val items:ArrayList<userinartist>, private val listener: itemsclicked):RecyclerView.Adapter<ViewHolderArtist>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderArtist {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.artistitems,parent,false)
        val viewholder=ViewHolderArtist(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolderArtist, position: Int) {
        val current = items[position]
        holder.username.text = current.email
        holder.decline.setOnClickListener{
        listener.userclicked(current.uid,current.email)
        }
        holder.accept.setOnClickListener{
            listener.acceptclicked(current.uid,current.email)
        }
    }


    override fun getItemCount(): Int {
    return items.size
    }

}

interface itemsclicked {
    fun userclicked(uid:String,mail:String)
    fun acceptclicked(uid: String,mail: String)
}

class ViewHolderArtist(itemview: View) :RecyclerView.ViewHolder(itemview){
    val username = itemview.findViewById<TextView>(R.id.username)
    val userpic  =  itemview.findViewById<ImageView>(R.id.userpic)
    val decline  =  itemview.findViewById<Button>(R.id.Decline)
    val accept   =  itemview.findViewById<Button>(R.id.Accept)



}
