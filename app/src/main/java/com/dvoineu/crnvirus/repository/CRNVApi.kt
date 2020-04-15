package com.dvoineu.crnvirus.repository

import com.dvoineu.crnvirus.model.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CRNVApi {

    @GET("/")
    fun getCountries(): Single<List<Country>>
//
//    @GET("/country/{countryName}")
//    fun getCountry(@Path("countryName)): Call<Country>
//
//    @GET("/total-deaths")
//    fun getTotalDeaths(): Call<Totals>
//
//    @GET("/total-confirmed")
//    fun getTotalConfirmed(): Call<Totals>
}