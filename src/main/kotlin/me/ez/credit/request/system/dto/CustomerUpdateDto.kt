package me.ez.credit.request.system.dto

import java.math.BigDecimal
import me.ez.credit.request.system.entity.Customer

data class CustomerUpdateDto (
        @field:NotEmpty(message = "Invalid input") val firstName: String,
        @field:NotEmpty(message = "Invalid input") val lastName: String,
        @field:NotEmpty(message = "Invalid input") val income:BigDecimal,
        @field:NotEmpty(message = "Invalid input") val zipCode: String,
        @field:NotEmpty(message = "Invalid input") val street: String,
){
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
