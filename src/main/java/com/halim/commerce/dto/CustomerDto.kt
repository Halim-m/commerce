package com.halim.commerce.dto

data class CustomerDto(
    val mail: String,
    val firstName: String,
    val lastName: String,
    val address: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomerDto

        if (mail != other.mail) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mail.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + address.hashCode()
        return result
    }
}
