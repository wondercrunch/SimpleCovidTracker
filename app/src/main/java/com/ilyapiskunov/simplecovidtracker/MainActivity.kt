package com.ilyapiskunov.simplecovidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilyapiskunov.simplecovidtracker.db.CountryStatDAO
import com.ilyapiskunov.simplecovidtracker.model.GlobalStat
import com.ilyapiskunov.simplecovidtracker.model.SummaryResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var globalStat : GlobalStat = GlobalStat(0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getApiResponse(true)
    }

    private fun getApiResponse(getStoredStatsOnFail: Boolean) {
        val apiCall = ApiInterface.create()
        apiCall.getSummary().enqueue(object : Callback<SummaryResponse?> {
            override fun onResponse(
                call: Call<SummaryResponse?>,
                response: Response<SummaryResponse?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    with (response.body()!!){

                        Log.i("Api response success", this.toString())

                        globalStat = global

                        rv_countries.layoutManager = LinearLayoutManager(this@MainActivity)
                        rv_countries.adapter = CountryStatAdapter(countries.sortedByDescending { country -> country.totalConfirmed }, this@MainActivity)

                        CountryStatDAO(this@MainActivity).store(countries)
                    }
                }
                else if (getStoredStatsOnFail) getStoredStats()
            }

            override fun onFailure(call: Call<SummaryResponse?>, t: Throwable) {
                Log.e("Api response error", null, t)
                if (getStoredStatsOnFail) getStoredStats()
            }
        })
    }


    private fun getStoredStats() {
        val storedStats = CountryStatDAO(this@MainActivity).get()

        globalStat = GlobalStat(storedStats.sumOf { countryStat -> countryStat.totalConfirmed },
            storedStats.sumOf { countryStat -> countryStat.totalDeaths })

        rv_countries.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_countries.adapter = CountryStatAdapter(storedStats, this@MainActivity)
    }

    private fun showGlobalStat() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.world_total_title))
            .setItems(arrayOf(getString(R.string.confirmed_format, globalStat.totalConfirmed),
                            getString(R.string.deaths_format, globalStat.totalDeaths)), null)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh ->
                getApiResponse(false)

            R.id.menu_global_stat ->
                showGlobalStat()
        }
        return true
    }
}