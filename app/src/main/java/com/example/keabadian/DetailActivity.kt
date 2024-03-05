package com.example.keabadian

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONTENT = "extra_content"
        const val EXTRA_TEXT = "extra_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvTitleDetailContent: TextView = findViewById(R.id.titleDetailContent)
        val tvDateDetailContent: TextView = findViewById(R.id.dateDetailContent)
        val tvLocationDetailContent: TextView = findViewById(R.id.locationDetailContent)
        val tvStoryDetailContent: TextView = findViewById(R.id.storyDetailContent)
        val photoDetailContent: ImageView = findViewById(R.id.photoDetailContent)
        val loveDetailContent: ImageButton = findViewById(R.id.loveButton)
        val shareDetailContene: ImageButton = findViewById(R.id.action_share)

        val dataContent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Content>(EXTRA_CONTENT, Content::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Content>(EXTRA_CONTENT)
        }
        if (dataContent != null) {
            tvTitleDetailContent.text = dataContent.titleContent
            tvDateDetailContent.text = dataContent.dateContent
            tvStoryDetailContent.text = dataContent.storyContent
            tvLocationDetailContent.text = dataContent.locationContent
            photoDetailContent.setImageResource(dataContent.storyPhoto)
            loveDetailContent.setOnClickListener{
                Toast.makeText(loveDetailContent.context,"Menyukai cerita " + tvTitleDetailContent.text + " !", Toast.LENGTH_SHORT ).show()
            }
            shareDetailContene.setOnClickListener{
                val sendIntent: Intent = Intent(). apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, tvTitleDetailContent.text.toString() + "\n" + tvStoryDetailContent.text.toString())
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

        }


    }
}