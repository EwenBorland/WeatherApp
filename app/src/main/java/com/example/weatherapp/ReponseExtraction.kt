package com.example.weatherapp

import org.json.JSONObject

fun getTemperature(response: String): MutableList<String>{
    val responseJson = JSONObject(response)
    val hourlyList = responseJson.getJSONObject("hourly").getJSONArray("data")
    val temperatures: MutableList<String> = ArrayList()

    for (i in 0 until hourlyList.length()){
        val hourObject = hourlyList.getJSONObject(i)
        val appendString = "" + hourObject["time"] + "\n" + hourObject["temperature"]
        temperatures.add(appendString)
    }

    return temperatures

}