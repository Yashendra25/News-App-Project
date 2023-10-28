package com.yashendra.news_app_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var address:String?
        setContentView(R.layout.activity_description)
        webView.webViewClient = WebViewClient()

        if(intent!=null){
            address=intent.getStringExtra("url")
            webView.loadUrl(address.toString())

        }else{
            finish()
            Toast.makeText(this@DescriptionActivity, "some unexpected error!!", Toast.LENGTH_SHORT).show()
        }



        // this will enable the javascript settings, it can also allow xss vulnerabilities
        webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)
    }
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}