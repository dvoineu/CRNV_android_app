package com.dvoineu.crnvirus.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dvoineu.crnvirus.model.CountryDatabase
import com.dvoineu.crnvirus.model.Totals
import kotlinx.coroutines.launch

class TotalsViewModel(application: Application): BaseViewModel(application) {
    val totalCasesLiveData = MutableLiveData<Int>()
    val totalDeathsLiveData = MutableLiveData<Int>()
    val totalRecoveredLiveData = MutableLiveData<Int>()

    fun fetchFromDBTotals(){
        launch {
            val totalCases = CountryDatabase(getApplication()).countryDao().getAllTotalCases()
            totalCasesLiveData.value = totalCases

            val totalDeaths = CountryDatabase(getApplication()).countryDao().getAllTotalDeaths()
            totalDeathsLiveData.value = totalDeaths

            val totalRecovered = CountryDatabase(getApplication()).countryDao().getAllTotalRecovered()
            totalRecoveredLiveData.value = totalRecovered
        }
    }

//    private fun totalsRetreived(totals: Int){
//        totalCasesLiveData.value = totals
//
//    }
}