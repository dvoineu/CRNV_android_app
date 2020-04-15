package com.dvoineu.crnvirus.model

import com.google.gson.annotations.SerializedName

data class Totals(
    val id: Int?,

    @SerializedName("total_cases")
    val totalCases: Int?,

    @SerializedName("new_cases")
    val newCases: Int?,

    @SerializedName("total_deaths")
    val totalDeaths: Int?,

    @SerializedName("new_deaths")
    val newDeaths: Int?,

    @SerializedName("total_recovered")
    val totalRecovered: Int?

)