package com.yashendra.news_app_project.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.yashendra.news_app_project.Adapter.DashboardRecyclerAdapter
import com.yashendra.news_app_project.MainActivity.Companion.currentselected
import com.yashendra.news_app_project.R
import com.yashendra.news_app_project.model.News
import com.yashendra.news_app_project.utill.ConnectionManager
import org.json.JSONException

class Dashboard : Fragment() {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var progressbarlayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    val NewsList= arrayListOf<News>()
    lateinit var recycleAdpater:DashboardRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerDashboard=view.findViewById(R.id.recyclerDashboard)
        layoutManager= LinearLayoutManager(activity)
        recycleAdpater= DashboardRecyclerAdapter(activity as Context,NewsList)

        progressbarlayout=view.findViewById(R.id.ProgreesbarLayout)
        progressBar=view.findViewById(R.id.Progressbar)
        progressbarlayout.visibility=View.VISIBLE




        if(ConnectionManager().checkconnectivity(activity as Context)){

            fetch()


        }
        else{
            val dialog= AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet is not found")
            dialog.setPositiveButton("Open Settings"){text,listener->
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()
                //do nothing
            }
            dialog.setNegativeButton("Exit"){text,listener->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()

        }










        return view
    }
    fun fetch(){
        val queue= Volley.newRequestQueue(activity as Context)
        var type="home"
        when(currentselected){
            1-> {
                type="sports"
            }
            2-> {
                type="health"
            }
            3-> {
                type="science"
            }
            4-> {
                type="entertainment"
            }
            5-> {
                type="technology"
            }

        }
        var url="https://newsapi.org/v2/top-headlines?country=in&apiKey=db7040fb057c4342b18cd9332fe4eba4"
        val url2="https://newsapi.org/v2/top-headlines?category=${type}&country=in&apiKey=db7040fb057c4342b18cd9332fe4eba4"
        if (type!="home") url=url2
        val jsonObjectRequest=object : JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener {
                try{
                    progressbarlayout.visibility=View.GONE
                    println("control aya1")
                    val success=it.getString("status")
                    println("control aya2")
                    if (success.equals("ok")){
                        val data=it.getJSONArray("articles")
                        for (i in 0 until data.length()){
                            val newsjsonobject=data.getJSONObject(i)
                            val newsobject=News(
                                newsjsonobject.getString("author"),
                                newsjsonobject.getString("title"),
                                newsjsonobject.getString("description"),
                                newsjsonobject.getString("url"),
                                newsjsonobject.getString("urlToImage"),
                                newsjsonobject.getString("publishedAt")
                            )
                            NewsList.add(newsobject)
                            recyclerDashboard.adapter=recycleAdpater
                            recyclerDashboard.layoutManager=layoutManager

                            //next two lines i removed because it can be done in the xml file using cardview to make ui more bettter
//                                recyclerDashboard.addItemDecoration(DividerItemDecoration(recyclerDashboard.context,
//                                    (layoutManager as LinearLayoutManager).orientation))

                        }
                    }
                    else{
                        Toast.makeText(activity as Context, "Unable to creceive the data from the server", Toast.LENGTH_SHORT).show()
                    }


                }
                catch (e: JSONException){
                    Toast.makeText(activity as Context, "Some unexpected Error", Toast.LENGTH_SHORT).show()
                }


            }, Response.ErrorListener {
                //here we handle the errors
//                    println("Error is $it")
                if (activity!=null){
                    Toast.makeText(activity as Context, "volley Error occured!!", Toast.LENGTH_SHORT).show()

                }


            }){
            //
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }

//                override fun getParams(): MutableMap<String, String>? {
//                    val para=HashMap<String,String>()
////                headers["Content-type"]="application/json"
//                    para["country"]="in"
//                    para["apiKey"]="db7040fb057c4342b18cd9332fe4eba4"
//                    return para
//                }



        }
        queue.add(jsonObjectRequest)


    }


}