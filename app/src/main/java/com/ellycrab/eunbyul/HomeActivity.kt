package com.ellycrab.eunbyul

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //랜덤 로고이미지
        val logoArray = arrayOf(
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five
        )

        //랜덤으로 로고 이미지 선택
        val randomIdx = (0 until logoArray.size).random()
        val selectedLogo = logoArray[randomIdx]

        //로고 이미지 설정
        val logo = findViewById<ImageView>(R.id.logo)
        logo.setImageResource(selectedLogo)

        val strId = intent.getStringExtra("id")
        val idRs = findViewById<TextView>(R.id.idResult)
        idRs.setText(strId)

        val btnFinish = findViewById<Button>(R.id.finishBtn)
        btnFinish.setOnClickListener {
            finish()
        }
    }
}