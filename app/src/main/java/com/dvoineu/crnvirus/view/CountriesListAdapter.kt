package com.dvoineu.crnvirus.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dvoineu.crnvirus.R
import com.dvoineu.crnvirus.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesListAdapter(val countriesList: ArrayList<Country>): RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder>() {

    fun updateCountriesList(newCountriesList: List<Country>){
        countriesList.clear()
        countriesList.addAll(newCountriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = countriesList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.tvCountryName.text = countriesList[position].countryName
        holder.view.tvTotalCases.text = countriesList[position].totalCases.toString()
        holder.view.tvNewCases.text = countriesList[position].newCases.toString()
        holder.view.tvTotalDeaths.text = countriesList[position].totalCases.toString()
        holder.view.tvNewDeaths.text = countriesList[position].newDeaths.toString()
        holder.view.tvTotalRecovered.text = countriesList[position].totalRecovered.toString()

        // Set Navigation from a list of countries to the detailed inforamtioin about the country
        holder.view.setOnClickListener{
            val action = StatisticsFragmentDirections.actionStatisticToCountryDetailFragment()
            action.countryUuid = countriesList[position].uuid
            Navigation.findNavController(it).navigate(action)
        }
    }

    class CountryViewHolder(var view: View): RecyclerView.ViewHolder(view)

}