package com.a2022sunrinhackathon.Activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a2022sunrinhackathon.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        binding.loginbtn.setOnClickListener {
            val emailText = binding.email.text
            val passwdText = binding.passwd.text

            val emailLength = binding.email.length()
            val passwdLength = binding.passwd.length()

            if(emailText != null && passwdText != null && emailLength != 0 && passwdLength != 0){
                signIn()
            }

            else{
                Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_LONG).show()
            }
        }

        binding.registerbtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    fun signIn() {
        auth?.signInWithEmailAndPassword(
            binding.email.text.toString(),
            binding.passwd.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    moveLoginPage(task.result?.user)
                } else {
                    Log.d(ContentValues.TAG, "failed")
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun moveLoginPage(user: FirebaseUser?){
        if(user != null) {
            Toast.makeText(this, "로그인이 완료되었습니다!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, AddPhotoActivity::class.java))
        }
    }
}