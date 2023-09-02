package com.dicoding.countryapps

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COUNTRY = "country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val Country = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Country>(EXTRA_COUNTRY, Country::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Country>(EXTRA_COUNTRY)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}