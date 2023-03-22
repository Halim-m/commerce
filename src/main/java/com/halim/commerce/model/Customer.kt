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
    val lastName:String,
    val address: String,
    var isActive: Boolean
) {
    constructor(mail: String, firstName: String, lastName: String, address: String) : this(null,mail,firstName, lastName, address, true)
}
