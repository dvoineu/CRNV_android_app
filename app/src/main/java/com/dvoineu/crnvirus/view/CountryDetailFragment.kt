package com.dvoineu.crnvirus.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get

import com.dvoineu.crnvirus.R
import com.dvoineu.crnvirus.model.Country
import com.dvoineu.crnvirus.viewmodel.CountryDetailViewModel
import kotlinx.android.synthetic.main.country_detail.*
import kotlinx.android.synthetic.main.fragment_statistics.*

/**
 * A simple [Fragment] subclass.
 */
class CountryDetailFragment : Fragment() {

    private lateinit var countryViewModel: CountryDetailViewModel
    private var countryUiid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUiid = CountryDetailFragmentArgs.fromBundle(it).countryUuid
        }

        countryViewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        countryViewModel.fetchFromDB(countryUiid)

        observeViewModel()

    }

    private fun observeViewModel(){
        countryViewModel.countryLiveData.observe(this, Observer { country ->
            country?.let {
                tvCountryName.text = country.countryName
                tvTotalCases.text = country.totalCases.toString()
                tvTotalDeaths.text = country.totalDeaths.toString()
            }

        })
    }




}