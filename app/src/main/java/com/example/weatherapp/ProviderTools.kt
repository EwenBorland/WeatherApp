package com.example.weatherapp

/**
 * A set of tools for interacting with the forecast providers
 * will have functions for requesting forecasts and adapting forecasts into the same format
 */

fun getProviderMap(): List<Map<String,String>>{
    return listOf(mapOf("name" to "provider 1","key" to "asdfghjkl123"), mapOf("name" to "provider dos","key" to "qwertyuiop9876"))
}

fun requestForecast(id: Int){

}