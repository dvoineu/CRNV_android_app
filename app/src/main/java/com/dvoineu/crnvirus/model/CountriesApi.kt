package com.dvoineu.crnvirus.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
    @GET("countries")
    fun getCountries(): Single<List<Country>>

    @GET("countries/total-confirmed")
    fun getTotalConfirmed(): Single<Country>
}