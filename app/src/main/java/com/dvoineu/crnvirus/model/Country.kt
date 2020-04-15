package com.dvoineu.crnvirus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(

    @ColumnInfo(name = "country_id")
    @SerializedName("_id")
    val id: String?,

    @ColumnInfo(name = "country_name")
    @SerializedName("country_name")
    val countryName: String?,

    @ColumnInfo(name = "total_cases")
    @SerializedName("total_cases")
    val totalCases: Int?,

    @ColumnInfo(name = "new_cases")
    @SerializedName("new_cases")
    val newCases: Int?,

    @ColumnInfo(name = "total_deaths")
    @SerializedName("total_deaths")
    val totalDeaths: Int?,

    @ColumnInfo(name = "new_deaths")
    @SerializedName("new_deaths")
    val newDeaths: Int?,

    @ColumnInfo(name = "total_recovered")
    @SerializedName("total_recovered")
    val totalRecovered: Int?

//    val imageUrl: String?


){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}