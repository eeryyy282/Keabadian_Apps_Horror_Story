package com.example.keabadian

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {
    private lateinit var rvContent: RecyclerView
    private var list = ArrayList<Content>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageSlider: ImageSlider = findViewById(R.id.image_slider)
        val images = ArrayList<SlideModel>()
        images.add(SlideModel(R.drawable.konten1, "Tirai dan Perempuan Merah", ScaleTypes.FIT))
        images.add(
            SlideModel(
                R.drawable.konten2,
                "Rumah Sakit Bekas Perang di Desaku",
                ScaleTypes.FIT
            )
        )
        images.add(SlideModel(R.drawable.konten3, "Tangan itu Melihatku Tertidur", ScaleTypes.FIT))
        imageSlider.setImageList(images)

        rvContent = findViewById(R.id.rv_content)
        rvContent.setHasFixedSize(true)

        list.addAll(getListContent())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListContent(): ArrayList<Content> {
        val dataTitle = resources.getStringArray(R.array.data_title_horror_content)
        val dataStory = resources.getStringArray(R.array.data_story_horror_content)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo_horror_content)
        val dataLocation = resources.getStringArray(R.array.data_location_horror_content)
        val dataDate = resources.getStringArray(R.array.data_date_horror_content)

        val listContent = ArrayList<Content>()
        for (i in dataTitle.indices) {
            val content = Content(
                dataTitle[i],
                dataStory[i],
                dataPhoto.getResourceId(i, -1),
                dataLocation[i],
                dataDate[i]
            )
            listContent.add(content)
        }
        dataPhoto.recycle()
        return listContent
    }


    private fun showRecyclerList() {
        rvContent.layoutManager = LinearLayoutManager(this)
        val listContentAdapter = ContentAdapter(list)
        rvContent.adapter = listContentAdapter

        listContentAdapter.setOnItemClickCallback(object : ContentAdapter.OnItemClickListener {
            override fun onItemClicked(data: Content) {
                showSelectedContent(data)
            }
        })
    }

    private fun showSelectedContent(content: Content) {
        Toast.makeText(
            this,
            "Kamu ingin membaca konten " + content.titleContent + " ?",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvContent.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                rvContent.layoutManager = GridLayoutManager(this, 2)
            }

            R.id.about_page -> {
                val moveToAboutPage = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveToAboutPage)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}