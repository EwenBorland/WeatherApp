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
import org.json.JSONObject

import java.util.*



class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var locationTextview: TextView
    private var weatherArray: MutableList<MutableList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MainAdapter(weatherArray, this)
        recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply{
            layoutManager = viewManager
            adapter = viewAdapter
        }

        locationTextview = findViewById(R.id.textView_location)

        val requestButton: Button = findViewById(R.id.request_button)
        requestButton.setOnClickListener{ getWeather(weatherArray) }
    }


    private fun getWeather(responseArray: MutableList<MutableList<String>>) {

        val queue = Volley.newRequestQueue(this)
        val loc: String = locationTextview.text.toString()
        val key: String = getString(R.string.DARKSKY_KEY)
        val url = "https://api.darksky.net/forecast/" + key + "/" + loc + "?exclude=[currently,minutely,daily]"
        Toast.makeText(this, url, Toast.LENGTH_LONG).show()
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    responseArray.add(0, getTemperature(response))
                    recyclerView.adapter?.notifyItemInserted(0)
                },
                Response.ErrorListener { url + " : " + getString(R.string.error_requestFailed) })
        // Toast.makeText(this, "making request", Toast.LENGTH_LONG).show()
        queue.add(stringRequest)
    }
}
