package com.ord.coronastats.data.model

import android.os.Parcel
import android.os.Parcelable


data class CountryStats (
    val updated: Long,
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Double,
    val country: String?,
    val critical: Int,
    val deaths: Int,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun toString(): String = "$country:\n$cases Cases\n$deaths Dead\n$recovered Recovered\n"

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(updated)
        parcel.writeInt(active)
        parcel.writeInt(cases)
        parcel.writeDouble(casesPerOneMillion)
        parcel.writeString(country)
        parcel.writeInt(critical)
        parcel.writeInt(deaths)
        parcel.writeInt(recovered)
        parcel.writeInt(todayCases)
        parcel.writeInt(todayDeaths)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryStats> {
        override fun createFromParcel(parcel: Parcel): CountryStats {
            return CountryStats(parcel)
        }

        override fun newArray(size: Int): Array<CountryStats?> {
            return arrayOfNulls(size)
        }
    }
}