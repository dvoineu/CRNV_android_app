package com.dvoineu.crnvirus.model

import com.google.gson.annotations.SerializedName

data class TotalDeaths (
    @SerializedName("total_deaths")
    val totalDeaths: String
)

