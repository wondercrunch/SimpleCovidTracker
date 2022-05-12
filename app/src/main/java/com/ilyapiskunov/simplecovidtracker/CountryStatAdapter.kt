package com.ilyapiskunov.simplecovidtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ilyapiskunov.simplecovidtracker.fragment.DetailsFragment
import com.ilyapiskunov.simplecovidtracker.model.CountryStat
import kotlinx.android.synthetic.main.country_item.view.*
import java.util.*

class CountryStatAdapter(private val countries: List<CountryStat>, private val context: Context): RecyclerView.Adapter<CountryStatAdapter.CountryStatViewHolder>() {

    inner class CountryStatViewHolder(val card: View) : RecyclerView.ViewHolder(card)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryStatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)

        return CountryStatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryStatViewHolder, position: Int) {
        val country = countries[position]
        with(holder) {

            card.tv_title.text = context.getString(
                R.string.country_name_format,
                countryCodeToEmojiFlag(country.code),
                country.name
            )
            card.tv_value.text = context.getString(
                R.string.confirmed_total_plus_new_format,
                country.totalConfirmed,
                country.newConfirmed
            )
            card.tv_date.text = DATE_FORMAT.format(country.date)

            //Открыть окно с подробной статистикой для этой страны
            card.setOnClickListener {
                val activity = context as AppCompatActivity
                val detailsFragment = DetailsFragment(country)
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.main, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun getItemCount(): Int = countries.size



}