package com.noby.core.models

data class  UserModel(
    var authToken: String?,
    var refreshToken: String?,
    var id: String?,
    var name: Name?,
    var username: String?,
    var shortName: String?,
    var nationalId: String?,
    var email: String?,
    var mobile: Mobile?,
    var gender: Int?,
    var isActive: Boolean?
)

data class Mobile(
    var countryCode: String?,
    var number: String?
)

data class Name(
    var arabic: String?,
    var english: String?,
    var currentLocalize: String?
)

fun getShortName(fullName: String) : String {
    return try {
        if(fullName.contains(" ")) {
            val split = fullName.split(" ")
            val firstNameChar = split[0].first()
            val lastNameChar = split[1].first()
            "$firstNameChar$lastNameChar"
        }else {
            val firstNameChar = fullName.first()
            "$firstNameChar"
        }
    }catch (e:Exception){
        ""
    }

}
