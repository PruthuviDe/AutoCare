package com.example.serviceapplication.model

data class UserModel(var username : String? = null,
                     var firstname : String? = null,
                     var lastname : String? = null,
                     var address : String? = null,
                     var tel : Int? = null,
                     var vehicles : ArrayList<VehicleModel>? = null)
