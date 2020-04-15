package com.dvoineu.crnvirus.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CountriesApiService {


    private val BASE_URL = "http://10.0.2.2:5000/api/v1/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}