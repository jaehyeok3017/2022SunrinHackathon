package com.a2022sunrinhackathon.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
            if(resultCode == Activity.RESULT_OK){
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
        //make filename
        var storage: FirebaseStorage? = FirebaseStorage.getInstance()
        var fbstore : FirebaseFirestore? = FirebaseFirestore.getInstance()

        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imageFileName = "IMAGE_" + timestamp + "_.png"
        var stoargeRef = storage?.reference?.child("images")?.child(imageFileName)

        stoargeRef?.putFile(photoUrl!!)?.addOnSuccessListener {
            var postDTO : postDTO = postDTO()
            postDTO.userId = auth?.currentUser?.uid
            postDTO.imageUrl = photoUrl.toString()
            postDTO.exaplain = binding.addEditText.text.toString()
            postDTO.address = binding.addressEditText.text.toString()

            fbstore!!.collection("posts").document(auth?.uid.toString()).set(postDTO)
            startActivity(Intent(this, SnsActivity::class.java))
        }
    }
}