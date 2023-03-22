package com.halim.commerce.model

import jakarta.persistence.*

@Entity(name = "Customer")
data class Customer(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    @Column(unique = true)
    var mail: String,
    var firstName: String,
    var lastName:String,
    var address: String,
    var isActive: Boolean
) {
    constructor(mail: String, firstName: String, lastName: String, address: String) : this(null,mail,firstName, lastName, address, true)
}
