package com.halim.commerce.dto.request

data class UpdateCustomerRequest(
    val id: Long,
    val mail: String,
    val firstName: String,
    val lastName: String,
    val address: String
) {

}
