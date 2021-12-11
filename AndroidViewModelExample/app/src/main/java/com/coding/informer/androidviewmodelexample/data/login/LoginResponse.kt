package com.coding.informer.androidviewmodelexample.data.login

/**
 * Created by AhmedEltaher
 */
data class LoginResponse(
    val id: String, val firstName: String, val lastName: String,
    val streetName: String, val buildingName: String,
    val postalCode: String, val state: String,
    val country: String, val email: String
) {
}