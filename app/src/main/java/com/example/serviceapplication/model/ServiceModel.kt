package com.example.serviceapplication.model


data class ServiceModel(var serviceName : String? = null,
                        var included : ArrayList<String>? = null,
                        var notincluded : ArrayList<String>? = null,
                        val price : String? = null,
                        val aditional : ArrayList<String>? = null) :java.io.Serializable
