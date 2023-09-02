package com.dicoding.countryapps

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.dicoding.countryapps.databinding.ActivityDetailBinding
import com.dicoding.countryapps.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var shareData: () -> Unit
    companion object {
        const val EXTRA_COUNTRY = "country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val country : Country? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Country>(EXTRA_COUNTRY, Country::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Country>(EXTRA_COUNTRY)
        }

        if (country == null) {
            return finish()
        }

        val (
            name,
            photo,
            _,
            detail,
            place,
            detailPhoto,
            stats,
        ) = country;

        Glide.with(this)
            .load(photo)
            .into(binding.countryFlag)

        Glide.with(this)
            .load(detailPhoto)
            .into(binding.countryPhoto)

        binding.countryDetail.text = detail
        binding.countryName.text = name
        binding.countryPlace.text = place
        binding.countryStats.text = stats

        shareData = {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Negara : $name\n" +
                        "Bendera : $photo\n" +
                        "Gambar Negara : $detailPhoto\n" +
                        "Status : $stats\n" +
                        "Tempat Wisata : $place\n" +
                        "Detail : $detail\n"
            )

            startActivity(Intent.createChooser(shareIntent, "Share using"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        menuInflater.inflate(R.menu.back_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_back -> {
                finish()
            }
            R.id.action_share -> {
                shareData()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}