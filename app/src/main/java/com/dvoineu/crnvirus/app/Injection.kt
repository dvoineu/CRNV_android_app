package com.dvoineu.crnvirus.app

import com.dvoineu.crnvirus.BuildConfig
import com.dvoineu.crnvirus.model.CountriesApi
import com.dvoineu.crnvirus.repository.CRNVApi
import com.dvoineu.crnvirus.repository.RemoteRepository
import com.dvoineu.crnvirus.repository.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Injection {
    fun provideRepository(): Repository = RemoteRepository
    const val BASE_URL1 = "http://localhost:5000/api/v1/countries"

    private fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if(BuildConfig.DEBUG){
            HttpLoggingInterceptor.Level.BODY
        }else{
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(provideLoggingInterceptor())
        return httpClient.build()
    }

    fun provideCRNVApi(): CRNVApi {
        return provideRetrofit().create(CRNVApi::class.java)
    }
}