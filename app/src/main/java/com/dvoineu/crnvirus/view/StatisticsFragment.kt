package com.dvoineu.crnvirus.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager

import com.dvoineu.crnvirus.R
import com.dvoineu.crnvirus.model.Country
import com.dvoineu.crnvirus.viewmodel.CountriesListViewModel
import com.dvoineu.crnvirus.viewmodel.TotalsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 */
class StatisticsFragment : Fragment() {

    private lateinit var countriesViewModel: CountriesListViewModel
    private lateinit var totalCasesViewModel: TotalsViewModel
    private val countriesListAdapter = CountriesListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesViewModel = ViewModelProviders.of(this).get(CountriesListViewModel::class.java)


        totalCasesViewModel = ViewModelProviders.of(this).get(TotalsViewModel::class.java)
        countriesViewModel.refresh()
        totalCasesViewModel.fetchFromDBTotals()


        rvCountriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesListAdapter
        }

        srlCountriesList.setOnRefreshListener {
            rvCountriesList.visibility = View.GONE
            tvCountriesListError.visibility = View.GONE
            pbLoadingView.visibility = View.VISIBLE

            // Data refreshed only if the user swipe or certain time passed otherwise data is retrieved from a local DB
            countriesViewModel.refreshBypassCache()

            srlCountriesList.isRefreshing = false
        }

        observeViewModel()
        observeTotalsViewModel()

    }

    fun observeTotalsViewModel(){
        totalCasesViewModel.totalCasesLiveData.observe(this, Observer {totalCases ->
            totalCases?.let {
                tvAllTotalCases.text = totalCases.toString()
            }
        })
        totalCasesViewModel.totalDeathsLiveData.observe(this, Observer { totalDeaths ->
            totalDeaths?.let {
                tvAllTotalDeaths.text = it.toString()
            }
        })
        totalCasesViewModel.totalRecoveredLiveData.observe(this, Observer { totalRecovered ->
            totalRecovered?.let {
                tvAllTotalRecovered.text = it.toString()
            }
        })

    }

    fun observeViewModel(){
        countriesViewModel.countries.observe(this, Observer {countries ->
            countries?.let{
                rvCountriesList.visibility = View.VISIBLE
                countriesListAdapter.updateCountriesList(countries)
            }
        })

        countriesViewModel.countriesLoadError.observe(this, Observer { isError ->
            isError?.let {
                tvCountriesListError.visibility = if(isError) View.VISIBLE else View.GONE
            }
        })

        countriesViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pbLoadingView.visibility = if(it) View.VISIBLE else View.GONE
                if (it){
                    rvCountriesList.visibility = View.GONE
                    tvCountriesListError.visibility = View.GONE
                }
            }
        })
    }




}
