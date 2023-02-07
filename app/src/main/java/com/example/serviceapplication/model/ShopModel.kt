package com.example.serviceapplication.model

import android.os.Parcel
import android.os.Parcelable

data class ShopModel(
    val name:String?, val address:String?, val delivery_charge:String?, val image:String?,
    val hours: Hours?, var autoParts: List<AutoParts?>?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Hours::class.java.classLoader),
        parcel.createTypedArrayList(AutoParts)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(delivery_charge)
        parcel.writeString(image)
        parcel.writeParcelable(hours, flags)
        parcel.writeTypedList(autoParts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShopModel> {
        override fun createFromParcel(parcel: Parcel): ShopModel {
            return ShopModel(parcel)
        }

        override fun newArray(size: Int): Array<ShopModel?> {
            return arrayOfNulls(size)
        }
    }
}



data class Hours(val Sunday:String?, val Monday:String?, val Tuesday:String?, val Wednesday:String?, val Thursday:String?,
                 val Friday:String?, val Saturday:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Sunday)
        parcel.writeString(Monday)
        parcel.writeString(Tuesday)
        parcel.writeString(Wednesday)
        parcel.writeString(Thursday)
        parcel.writeString(Friday)
        parcel.writeString(Saturday)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hours> {
        override fun createFromParcel(parcel: Parcel): Hours {
            return Hours(parcel)
        }

        override fun newArray(size: Int): Array<Hours?> {
            return arrayOfNulls(size)
        }
    }
}



data class AutoParts(val name:String?, val price:Float, val url:String?, var totalInCart:Int) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeFloat(price)
        parcel.writeString(url)
        parcel.writeInt(totalInCart)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AutoParts> {
        override fun createFromParcel(parcel: Parcel): AutoParts {
            return AutoParts(parcel)
        }

        override fun newArray(size: Int): Array<AutoParts?> {
            return arrayOfNulls(size)
        }
    }
}
