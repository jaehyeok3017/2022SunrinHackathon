package com.a2022sunrinhackathon.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a2022sunrinhackathon.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        binding.registercomplete.setOnClickListener {
            binding.emailCheckText.visibility = View.INVISIBLE
            binding.passwdCheckText.visibility = View.INVISIBLE
            binding.repeatPasswdCheckText.visibility = View.INVISIBLE

            val checkEmailResult = checkEmail()
            val checkPasswdResult = checkPasswd()
            val checkRepeatPasswdResult = checkRepeatPasswd()

            if(checkEmailResult && checkPasswdResult && checkRepeatPasswdResult){
                createUser()
            }

            else{
                Toast.makeText(this, "각 형식을 확인해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checkEmail(): Boolean {
        val email = binding.registeremail.text.toString()
        val emailFormatCheck = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val p = Pattern.matches(emailFormatCheck, email)

        when(p){
            true -> return true
            false -> {
                binding.emailCheckText.visibility = View.VISIBLE
                return false
            }
        }
    }

    fun checkPasswd(): Boolean {
        val passwd = binding.registerpasswd.text.toString()
        val passwdFormatCheck = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&])[A-Za-z[0-9]\$@\$!%*#?&]{8,20}\$"
        val p = Pattern.matches(passwdFormatCheck, passwd)

        when(p){
            true -> return true
            false -> {
                binding.passwdCheckText.visibility = View.VISIBLE
                return false
            }
        }
    }

    fun checkRepeatPasswd(): Boolean {
        val passwd = binding.registerpasswd.text.toString()
        val repeatPasswd = binding.registerRepeatPasswd.text.toString()
        val p = passwd == repeatPasswd

        when(p){
            true -> return true
            false -> {
                binding.repeatPasswdCheckText.visibility = View.VISIBLE
                return false
            }
        }
    }

    fun createUser(){
        auth?.createUserWithEmailAndPassword(
            binding.registeremail.text.toString(),
            binding.registerpasswd.text.toString()
        )
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    moveLoginPage(task.result?.user)
                } else if(task.exception?.message.isNullOrEmpty()){
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                } else {
                    signIn()
                }
            }
    }

    fun signIn() {
        auth?.signInWithEmailAndPassword(
            binding.registeremail.text.toString(),
            binding.registerpasswd.text.toString()
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
            Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}