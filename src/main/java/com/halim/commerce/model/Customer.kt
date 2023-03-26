package com.halim.commerce.model

import jakarta.persistence.*

@Entity(name = "Customer")
data class Customer(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    @Column(unique = true)
    val mail: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    var isActive: Boolean
) {
    constructor(mail: String, firstName: String, lastName: String, address: String) : this(
        null,
        mail,
        firstName,
        lastName,
        address,
        true
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (mail != other.mail) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (address != other.address) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + mail.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + isActive.hashCode()
        return result
    }
}
