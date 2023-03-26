package com.halim.commerce.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class CreateCustomerRequest(
    @field:NotNull(message = "Mail must not be null.")
    @field:NotBlank(message = "Mail must not be empty.")
    val mail: String,
    @field:NotNull(message = "Firstname must not be null.")
    @field:NotBlank(message = "Firstname must not be empty.")
    val firstName: String,
    @field:NotNull(message = "Lastname must not be null.")
    @field:NotBlank(message = "Lastname must not be empty.")
    val lastName: String,
    @field:NotNull(message = "Address must not be null.")
    @field:NotBlank(message = "Address must not be empty.")
    val address: String
)
