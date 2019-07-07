package com.example.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*



class MainActivity : AppCompatActivity() {

    lateinit var locationTextview: TextView
    lateinit var myResponseHolder: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextview = findViewById(R.id.textView_location)
        myResponseHolder = findViewById(R.id.box_thing)
        val requestButton: Button = findViewById(R.id.request_button)
        requestButton.setOnClickListener{ getWeather(myResponseHolder) }
    }


    private fun getWeather(response_out: TextView) {

        val queue = Volley.newRequestQueue(this)
        val loc: String = locationTextview.text.toString()
        val key: String = getString(R.string.DARKSKY_KEY)
        val url = "https://api.darksky.net/forecast/" + key + "/" + loc + "?exclude=[currently,minutely,daily]"
        Toast.makeText(this, url, Toast.LENGTH_LONG).show()
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    response_out.setText(response)

                },
                Response.ErrorListener { url + " : " + getString(R.string.error_requestFailed) })
        // Toast.makeText(this, "making request", Toast.LENGTH_LONG).show()
        queue.add(stringRequest)
    }
}
