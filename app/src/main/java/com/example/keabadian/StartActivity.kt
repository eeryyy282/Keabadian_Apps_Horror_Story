package com.example.keabadian

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnMoveWithName: Button
    private lateinit var textName: EditText

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnMoveWithName = findViewById(R.id.button_move_with_name)
        textName = findViewById(R.id.text_name)

        btnMoveWithName.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button_move_with_name) {
            var isEmptyFields = false
            var textKosong: String = "Nama anda tidak boleh kosong"
            val userName = textName.text.toString()
            if (userName.isEmpty()) {
                isEmptyFields = true
                textName.error = textKosong
            }
            if (!isEmptyFields) {
                val resultUserName =
                    Intent(this@StartActivity, WelcomeNameSplash::class.java).apply {
                        putExtra(EXTRA_NAME, userName)
                    }
                startActivity(resultUserName)
            }
        }
    }
}