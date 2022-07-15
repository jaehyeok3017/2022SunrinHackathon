package com.a2022sunrinhackathon.Activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a2022sunrinhackathon.R
import com.a2022sunrinhackathon.databinding.ActivityAddPhotoBinding
import com.a2022sunrinhackathon.databinding.ActivityRegisterBinding
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var photoUrl: Uri? = null

    private lateinit var binding: ActivityAddPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_add_photo)

        var uploadButton = findViewById<Button>(R.id.add_btn)

        //Initiate storage
        storage = FirebaseStorage.getInstance()

        //Open the album
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)

        //add image upload event
        uploadButton.setOnClickListener {
            contentUpload()
        }

        val add_rating = findViewById<RatingBar>(R.id.add_rating)
        add_rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> add_rating.rating = rating }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var addPhotoImage = findViewById<ImageView>(R.id.add_image)
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM){
            if(resultCode == Activity.RESULT_OK){
                //path to the selected image
                photoUrl = data?.data
                addPhotoImage.setImageURI(photoUrl)
            }else{
                //cancel
                finish()
            }
        }
    }


    fun contentUpload() {
        //make filename
        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imageFileName = "IMAGE_" + timestamp + "_.png"
        var stoargeRef = storage?.reference?.child("images")?.child(imageFileName)

        stoargeRef?.putFile(photoUrl!!)?.addOnSuccessListener {
            Toast.makeText(this,"image Uploaded", Toast.LENGTH_SHORT).show()
        }
    }


}