package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    lateinit var edtUsername: EditText
    lateinit var edtEmail:EditText
    lateinit var edtPassword: EditText
    lateinit var edtCPassword:EditText
    lateinit var btnSignup: Button
    lateinit var tvLogin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        edtUsername = findViewById(R.id.mEdtUsername)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtPassword = findViewById(R.id.mEdtPassword)
        edtCPassword = findViewById(R.id.mEdtCPassword)
        btnSignup= findViewById(R.id.mBtnSignup)
        tvLogin = findViewById(R.id.mTvLogin)

        auth = FirebaseAuth.getInstance()

        // Set onClick listener to the button and textview
        btnSignup.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val cpassword = edtCPassword.text.toString().trim()

            if (username.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || cpassword.isNotEmpty()){
                if (password == cpassword){

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,SigninActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(),
                            Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Please check your password again",
                    Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please fill all the fields",
                Toast.LENGTH_SHORT).show()
            }
        }
        tvLogin.setOnClickListener {
            var intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
    }
}