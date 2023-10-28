package com.yashendra.news_app_project

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.yashendra.news_app_project.fragments.Dashboard


class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorlayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationview: NavigationView
    var previousmenuitem: MenuItem?=null
    lateinit var tabLayout: TabLayout
    companion object{
        var currentselected:Int=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout=findViewById(R.id.drawerLayout)
        coordinatorlayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frame)
        navigationview=findViewById(R.id.navigationView)
        tabLayout=findViewById(R.id.include)
        setuptoolbar()
        opendasboard()




        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0-> {
                        currentselected=0
                        opendasboard()
                        Toast.makeText(this@MainActivity, "home", Toast.LENGTH_SHORT).show()

                    }
                    1-> {
                        currentselected=1
                        opendasboard()
                        Toast.makeText(this@MainActivity, "sports", Toast.LENGTH_SHORT).show()
                    }
                    2-> {
                        currentselected=2
                        opendasboard()
                        Toast.makeText(this@MainActivity, "health", Toast.LENGTH_SHORT).show()
                    }
                    3-> {
                        currentselected=3
                        opendasboard()
                        Toast.makeText(this@MainActivity, "science ", Toast.LENGTH_SHORT).show()
                    }
                    4-> {
                        currentselected=4
                        opendasboard()
                        Toast.makeText(this@MainActivity, "entertainment ", Toast.LENGTH_SHORT).show()
                    }
                    5-> {
                        currentselected=5
                        opendasboard()
                        Toast.makeText(this@MainActivity, "technology", Toast.LENGTH_SHORT).show()
                    }

                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        navigationview.setNavigationItemSelectedListener{
            if (previousmenuitem!=null){
                previousmenuitem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousmenuitem=it

            when(it.itemId){
                R.id.dashboard ->{
                    Toast.makeText(this, "dashboard", Toast.LENGTH_SHORT).show()
                    tabLayout.visibility=View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, Dashboard())
//                        .addToBackStack("Dashboard")
                        .commit()
                    supportActionBar?.title="Dashboard"
                    drawerLayout.closeDrawers()
                }
                R.id.favourites ->{
                    Toast.makeText(this, "favourites", Toast.LENGTH_SHORT).show()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame, FavouritesFragment())
////                        .addToBackStack("favourites")
//                        .commit()
//                    supportActionBar?.title="Favourites"
//                    drawerLayout.closeDrawers()
                }
                R.id.profile ->{
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame, ProfileFragment())
////                        .addToBackStack("profile")
//                        .commit()
//                    supportActionBar?.title="Profile"
//                    drawerLayout.closeDrawers()
                }
                R.id.AboutApp ->{
                    Toast.makeText(this, "About app", Toast.LENGTH_SHORT).show()
                    tabLayout.visibility=View.GONE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,AboutFragment())
//                        .addToBackStack("about app")
                        .commit()
                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()

                }
            }
            return@setNavigationItemSelectedListener true
        }


    }
    fun setuptoolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="ToolBaar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBarDrawerToggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,
            R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()


    }
    fun opendasboard(){
        val fragment= Dashboard()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        supportActionBar?.title="DashBoard"
        navigationview.setCheckedItem(R.id.dashboard)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)

        }

        return super.onOptionsItemSelected(item)
    }





    }
