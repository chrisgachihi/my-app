package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
    private lateinit var auth:  FirebaseAuth
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var tvSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtPassword = findViewById(R.id.mEdtPassword)
        btnLogin = findViewById(R.id.mBtnLogin)
        tvSignup = findViewById(R.id.mTvSignup)

        auth = FirebaseAuth.getInstance()

        //Set OnClick Listener on the button and text view
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val result = task.result
                        if (result != null && result.signInMethods != null && result.signInMethods!!.contains(
                                EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)){
                            //The email address is already registered,so prompt the user to sign in instead.
                            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                                if (it.isSuccessful){
                                    val intent = Intent(this, PageActivity::class.java)
                                    startActivity(intent)
                                }else{
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            //The email address is not registered,so show an error message to the user.
                            Toast.makeText(this, "Email address is not registered.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
        tvSignup.setOnClickListener {
            var intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

    }
}