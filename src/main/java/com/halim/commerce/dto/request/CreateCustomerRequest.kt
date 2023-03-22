package com.halim.commerce.dto.request

data class CreateCustomerRequest(
    val mail: String,
    val firstName: String,
    val lastName: String,
    val address: String
)
