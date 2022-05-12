package com.ilyapiskunov.simplecovidtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ilyapiskunov.simplecovidtracker.R
import com.ilyapiskunov.simplecovidtracker.countryCodeToEmojiFlag
import com.ilyapiskunov.simplecovidtracker.model.CountryStat
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment(private val country: CountryStat) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        view.findViewById<TextView>(R.id.tv_details_title).text = getString(R.string.country_name_format, countryCodeToEmojiFlag(country.code), country.name)
        view.findViewById<TextView>(R.id.tv_details_new_confirmed).text = getString(R.string.confirmed_format, country.newConfirmed)
        view.findViewById<TextView>(R.id.tv_details_total_confirmed).text = getString(R.string.confirmed_format, country.totalConfirmed)
        view.findViewById<TextView>(R.id.tv_details_new_deaths).text = getString(R.string.deaths_format, country.newDeaths)
        view.findViewById<TextView>(R.id.tv_details_total_deaths).text = getString(R.string.deaths_format, country.totalDeaths)
        view.findViewById<TextView>(R.id.tv_details_new_recovered).text = getString(R.string.recovered_format, country.newRecovered)
        view.findViewById<TextView>(R.id.tv_details_total_recovered).text = getString(R.string.recovered_format, country.totalRecovered)
        return view
    }

}