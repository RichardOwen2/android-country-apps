package com.dicoding.countryapps

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.countryapps.databinding.ItemRowCountryBinding

class ListCountryAdapter(private val listCountry: ArrayList<Country>) : RecyclerView.Adapter<ListCountryAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val country = listCountry[position]
        Glide.with(holder.itemView.context)
            .load(country.photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = country.name
        holder.binding.tvItemDescription.text = country.description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_COUNTRY, country)
            }

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listCountry.size

    class ListViewHolder(var binding: ItemRowCountryBinding) : RecyclerView.ViewHolder(binding.root)
}