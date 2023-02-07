package com.example.serviceapplication.model

import java.util.*

data class OrderModel(var orderId : String? = null,
                      var userName : String? = null,
                      var vehicleNumber : String? = null,
                      var serviceModel : ServiceModel? = null,
                      var datetime: Date? = null) :java.io.Serializable
