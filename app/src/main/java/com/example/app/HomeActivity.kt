package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var ivMen: ImageView
    lateinit var ivWomen: ImageView
    lateinit var tvClasses: TextView
    lateinit var tvCommence: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ivMen= findViewById(R.id.mIvMen)
        ivWomen = findViewById(R.id.mIvWomen)
        tvClasses = findViewById(R.id.mTvClasses)
        tvCommence = findViewById(R.id.mTvCommence)

        ivMen.setOnClickListener {
            var intent = Intent(this,PageActivity::class.java)
            startActivity(intent)
        }
        setContentView(R.layout.activity_home)
    }
}