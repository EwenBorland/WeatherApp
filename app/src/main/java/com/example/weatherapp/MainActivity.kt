package com.example.weatherapp


import android.os.Bundle

import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

import java.util.*



class MainActivity : AppCompatActivity() {

    private val forecastFragment: ForecastFragment = ForecastFragment()
    private val locationFragment: LocationFragment = LocationFragment()
    private val settingsFragment: SettingsFragment = SettingsFragment()
    private val mapFragment: MapFragment = MapFragment()
    private val fragmentMap: Map<String, Fragment> = mapOf<String, Fragment>(
        "forecastFragment" to forecastFragment,
        "locationFragment" to locationFragment,
        "settingsFragment" to settingsFragment,
        "mapFragment" to mapFragment
    )
    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment("forecastFragment")

    }

    fun replaceFragment(fragment_key: String) {
        //function to change the fragment currently in the main fragment frame layout
        supportFragmentManager.beginTransaction().apply {
            fragmentMap[fragment_key]?.let { replace(R.id.frameLayoutForFragments, it) }
            addToBackStack(null)
            commit()
        }
    }

}

