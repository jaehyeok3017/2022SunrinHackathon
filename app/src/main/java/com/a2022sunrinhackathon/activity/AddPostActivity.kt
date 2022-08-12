package com.a2022sunrinhackathon.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.a2022sunrinhackathon.R
import com.a2022sunrinhackathon.data.firebase.postDTO
import com.a2022sunrinhackathon.databinding.ActivityAddPostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class AddPostActivity : AppCompatActivity() {
    var PICK_IMAGE_FROM_ALBUM = 0
    val db = FirebaseFirestore.getInstance()
    var store: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    var photoUrl: Uri? = null

    private lateinit var binding: ActivityAddPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Initiate storage
        auth = FirebaseAuth.getInstance()
        store = FirebaseFirestore.getInstance()

        binding.addImage.setOnClickListener { openAlbum() }
        binding.addBtn.setOnClickListener { dataUpload() }

        val add_rating = findViewById<RatingBar>(R.id.add_rating)
        add_rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> add_rating.rating = rating }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var addPhotoImage = findViewById<ImageView>(R.id.add_image)
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM){
            if(resultCode == RESULT_OK){
                photoUrl = data?.data
                addPhotoImage.setImageURI(photoUrl)
            }
            else{
                finish()
            }
        }
    }

    private fun openAlbum(){
        //Open the album
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
    }

    private fun dataUpload() {
        val storage: FirebaseStorage? = FirebaseStorage.getInstance()
        var fbstore : FirebaseFirestore? = FirebaseFirestore.getInstance()

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "IMAGE_" + timestamp + "_.png"
        val stoargeRef = storage?.reference?.child("images")?.child(imageFileName)

        stoargeRef?.putFile(photoUrl!!)?.addOnSuccessListener {
            stoargeRef.downloadUrl.addOnSuccessListener { uri ->
                val postDTO = postDTO()

                postDTO.userId = auth?.currentUser?.uid
                postDTO.imageUrl = uri.toString()
                postDTO.comment = binding.commentEditText.text.toString()
                postDTO.address = binding.addressEditText.text.toString()
                postDTO.rating = binding.addRating.rating.toLong()
                postDTO.timeStamp = System.currentTimeMillis()

                db.collection("posts")
                    .add(postDTO)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                        startActivity(Intent(this, SnsActivity::class.java))
                    }
                    .addOnFailureListener{ e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
        }
    }
}