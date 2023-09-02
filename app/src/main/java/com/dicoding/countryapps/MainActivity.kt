package com.dicoding.countryapps

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.countryapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCountry.setHasFixedSize(true)

        list.addAll(getCountryList())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    private fun getCountryList(): ArrayList<Country> {
        val name = resources.getStringArray(R.array.data_name)
        val description = resources.getStringArray(R.array.data_description)
        val detail = resources.getStringArray(R.array.data_detail)
        val photo = resources.getStringArray(R.array.data_photo)
        val detailPhoto = resources.getStringArray(R.array.data_detail_photo)
        val place = resources.getStringArray(R.array.data_place)
        val stats = resources.getStringArray(R.array.data_stats)

        val listCountry = ArrayList<Country>()

        for (i in name.indices) {
            listCountry.add(Country(
                name = name[i],
                description = description[i],
                detail = detail[i],
                photo = photo[i],
                detailPhoto = detailPhoto[i],
                place = place[i],
                stats = stats[i],
            ))
        }

        return listCountry;
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvCountry.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvCountry.layoutManager = LinearLayoutManager(this)
        }

        val listCountryAdapter = ListCountryAdapter(list)
        binding.rvCountry.adapter = listCountryAdapter
    }
}