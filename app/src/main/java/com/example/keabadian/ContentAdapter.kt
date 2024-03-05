package com.example.keabadian

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContentAdapter(private val listContent: ArrayList<Content>): RecyclerView.Adapter<ContentAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickListener

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleContent: TextView = itemView.findViewById(R.id.titleContent)
        val imgContent: ImageView = itemView.findViewById(R.id.contentPhoto)
        val story: TextView = itemView.findViewById(R.id.storyContent)
        val location: TextView = itemView.findViewById(R.id.locationContent)
        val date: TextView = itemView.findViewById(R.id.dateContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_content, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listContent.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (titleContent, story, imgContent, locationContent, dateContent) = listContent[position]
        holder.titleContent.text = titleContent
        holder.imgContent.setImageResource(imgContent)
        holder.story.text = story
        holder.location.text = locationContent
        holder.date.text = dateContent

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("extra_content", listContent[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    fun setOnItemClickCallback(onItemClickListener: OnItemClickListener) {
        this.onItemClickCallback = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(data: Content)
    }
}