package com.example.artistdashboard

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UploadImage : AppCompatActivity() {
    lateinit var gallery:Button
    lateinit var upload:Button
    lateinit var image:ImageView
    final val req_code:Int=1000
    var storageref = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
       // storageref=FirebaseStorage.
        Log.d(TAG,"create called")
        gallery=findViewById(R.id.gallery)
        upload =findViewById(R.id.upload)
        image  =findViewById(R.id.image)

        gallery.setOnClickListener {
            val igallery = Intent(Intent.ACTION_PICK)
            igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(igallery, req_code)
//        val galleryimage = registerForActivityResult(
//            ActivityResultContracts.GetContent())
//          {
//                image.setImageURI(it)
//            }
//        gallery.setOnClickListener{
//            galleryimage.launch("image/*")
//        }
//    }
        }

}

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Start called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Stop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Destroy called")
    }

//
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== RESULT_OK)
        {
            if(requestCode==req_code)
            {
                image.setImageURI(data!!.data)

                upload.setOnClickListener{

                }

            }
        }
    }

}