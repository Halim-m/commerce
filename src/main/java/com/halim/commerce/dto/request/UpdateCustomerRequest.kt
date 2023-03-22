package com.halim.commerce.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class UpdateCustomerRequest(
    @field:NotNull(message = "id must not be null.")
    @field:Positive(message = "id must be greater than zero")
    val id: Long,
    @field:NotNull(message = "Mail must not be null.")
    @field:NotEmpty(message = "Mail must not be empty")
    val mail: String,
    @field:NotNull(message = "Firstname must not be null.")
    @field:NotEmpty(message = "Firstname must not be empty.")
    val firstName: String,
    @field:NotNull(message = "Lastname must not be null.")
    @field:NotEmpty(message = "Lastname must not be empty.")
    val lastName: String,
    @field:NotNull(message = "Address must not be null.")
    @field:NotEmpty(message = "Address must not be empty.")
    val address: String
) {

}
