package com.example.weatherapp


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_forecast.*
import java.util.ArrayList

/**
 Forecast Fragment
 the home view of the app, shows weather forecast for all locations, can navigate to settings
 */
class ForecastFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var locationTextview: TextView
    private var weatherArray: MutableList<MutableList<String>> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(this.context)
        this.context?.let {
            viewAdapter = MainAdapter(weatherArray, it)
        }
        recyclerView = main_recycler_view.apply{
            layoutManager = viewManager
            adapter = viewAdapter
        }

        locationTextview = textView_location

        val requestButton: Button = request_button
        requestButton.setOnClickListener{ getWeather(weatherArray) }
//        requestButton.setOnClickListener{(activity as MainActivity).replaceFragment("hiya")}
    }

    private fun getWeather(responseArray: MutableList<MutableList<String>>) {

        val queue = Volley.newRequestQueue(this.context)
        val loc: String = locationTextview.text.toString()
        val key: String = getString(R.string.DARKYSKY_KEY)
        val url = "https://api.darksky.net/forecast/" + key + "/" + loc + "?exclude=[currently,minutely,daily]"
        Toast.makeText(this.context, url, Toast.LENGTH_LONG).show()
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                responseArray.add(0, getTemperature(response))
                recyclerView.adapter?.notifyItemInserted(0)
            },
            Response.ErrorListener { url + " : " + getString(R.string.error_requestFailed) })
        // Toast.makeText(this, "making request", Toast.LENGTH_LONG).show()
        queue.add(stringRequest)
    }
}
