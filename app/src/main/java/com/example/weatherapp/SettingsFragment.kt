package com.example.weatherapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
val provider_list: List<Map<String,String>> =  getProviderMap()
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listAdapter = ArrayAdapter(this.context, R.layout.provider_list_item, provider_list)
        providerListView.adapter = listAdapter
    }


}

private class ProviderListAdapter(providers: Map<String, String>): BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

}

private class Aaas
