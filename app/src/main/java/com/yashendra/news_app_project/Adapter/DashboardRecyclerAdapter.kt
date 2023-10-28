package com.yashendra.news_app_project.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yashendra.news_app_project.DescriptionActivity
import com.yashendra.news_app_project.R
import com.yashendra.news_app_project.model.News
import java.util.ArrayList

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<News>): RecyclerView.Adapter<DashboardRecyclerAdapter.Dashboardviewholder>() {
    class Dashboardviewholder(view: View):RecyclerView.ViewHolder(view){
        val mainheading: TextView =view.findViewById(R.id.maingeading)
        val content: TextView =view.findViewById(R.id.content)
        val author: TextView =view.findViewById(R.id.author)
        val time: TextView =view.findViewById(R.id.time)
        val imageview: ImageView =view.findViewById(R.id.imageview)
        val cardview: CardView =view.findViewById(R.id.cardview)

    }
    override fun onBindViewHolder(holder: Dashboardviewholder, position: Int) {
//        val text=itemList[position]
//        holder.textView.text=text
        val news=itemList[position]
        holder.author.text=news.author
        holder.mainheading.text=news.title
        holder.content.text=news.description
        holder.time.text=news.publishedAt

        //holder.imgbookimage.setImageResource(book.bookimage)
        Picasso.get().load(news.urltoImage).error(R.drawable.ic_launcher_background).into(holder.imageview)

        holder.cardview.setOnClickListener{
            val intent= Intent(context, DescriptionActivity::class.java)
            intent.putExtra("url",news.url)
            context.startActivity(intent)

//            Toast.makeText(context, "Clicked on ${holder.mainheading.text}", Toast.LENGTH_SHORT).show()
        }


    }

    override fun getItemCount(): Int {
        return itemList.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dashboardviewholder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.singlerow, parent ,false)
        return Dashboardviewholder(view)
    }

}