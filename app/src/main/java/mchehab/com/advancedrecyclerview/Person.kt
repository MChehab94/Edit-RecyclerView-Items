package mchehab.com.advancedrecyclerview

import android.os.Parcel
import android.os.Parcelable

class Person : Parcelable{

    var firstName: String? = null
    var lastName: String? = null

    constructor() {
        lastName = ""
        firstName = lastName
    }

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }

    protected constructor(`in`: Parcel) {
        firstName = `in`.readString()
        lastName = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(firstName)
        dest.writeString(lastName)
    }

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<Person> = object : Parcelable.Creator<Person> {
            override fun createFromParcel(`in`: Parcel): Person {
                return Person(`in`)
            }

            override fun newArray(size: Int): Array<Person?> {
                return arrayOfNulls(size)
            }
        }
    }
}