package com.dvoineu.crnvirus.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvoineu.crnvirus.model.CountriesApiService
import com.dvoineu.crnvirus.model.Country
import com.dvoineu.crnvirus.model.CountryDatabase
import com.dvoineu.crnvirus.model.Totals
import com.dvoineu.crnvirus.util.SharedPreferencesHelper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class CountriesListViewModel(application: Application) : BaseViewModel(application) {

    @InternalCoroutinesApi
    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5*60*1000*1000*1000L

    private val countriesService = CountriesApiService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
//    val allTotalCases = MutableLiveData<Totals>()
    val countriesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // data retrieved from remote only if time passed since last remote update was more than 5 minutes
    @InternalCoroutinesApi
    fun refresh() {
        val updateTime = prefHelper.getUpdateTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            fetchFromDatabase()
        }else{
            fetchFromRemote()
        }
    }

    @InternalCoroutinesApi
    fun refreshBypassCache(){
        fetchFromRemote()
    }

    private fun fetchFromDatabase(){
        loading.value = true
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            countriesRetrieved(countries)
            Toast.makeText(getApplication(), "Data retrieved from database", Toast.LENGTH_SHORT).show()
        }
    }

    @InternalCoroutinesApi
    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {

                    override fun onSuccess(countriesList: List<Country>) {
                        storeCountriesLocally(countriesList)
                        Toast.makeText(getApplication(), "Data retrieved from remote", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        countriesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun countriesRetrieved(countriesList: List<Country>) {
        countries.value = countriesList
        countriesLoadError.value = false
        loading.value = false
    }

    @InternalCoroutinesApi
    private fun storeCountriesLocally(list: List<Country>) {
        // launches task on a separate Thread
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            // first delete previous data in order not to polute old data with new data
            dao.deleteAllCountries()
            // *list means it takes a list and expands it to individual elements
            // result returns a list of uiid's
            val result: List<Long> = dao.insertAll(*list.toTypedArray())

            // '0 until list.size' means not including index list.size
            for(i in 0 until list.size){
                list[i].uuid = result[i].toInt()
            }
            countriesRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}