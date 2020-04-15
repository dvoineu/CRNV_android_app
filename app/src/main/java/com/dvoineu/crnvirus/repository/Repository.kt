package com.dvoineu.crnvirus.repository

import androidx.lifecycle.LiveData
import com.dvoineu.crnvirus.model.Country
import com.dvoineu.crnvirus.model.Totals

interface Repository {

    interface Repository {
        fun getCountries(): LiveData<List<Country>>
        fun getCountry(): LiveData<Country>
        fun getTotalDeaths(): LiveData<Totals>
        fun getTotalConfirmed(): LiveData<Totals>
    }
}