package com.dvoineu.crnvirus.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvoineu.crnvirus.model.Country
import com.dvoineu.crnvirus.model.CountryDatabase
import kotlinx.coroutines.launch

class CountryDetailViewModel(application: Application): BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun fetchFromDB(uuid: Int){
        launch {
            val country = CountryDatabase(getApplication()).countryDao().getCountry(uuid)
            countryRetrieved(country)
            Toast.makeText(getApplication(), "Data retrieved from database", Toast.LENGTH_SHORT).show()
//            Log.i("Coutry.field", country.totalCases!!)
        }
    }

    private fun countryRetrieved(country: Country) {
        countryLiveData.value = country
    }




}