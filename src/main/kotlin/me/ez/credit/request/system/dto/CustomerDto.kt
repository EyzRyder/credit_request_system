package me.ez.credit.request.system.dto

import me.ez.credit.request.system.entity.Customer
import me.ez.credit.request.system.entity.Address
import java.math.BigDecimal

data class CustomerDto(
 @field:NotEmpty(message = "Invalid input") val firstName: String,
 @field:NotEmpty(message = "Invalid input") val lastName: String,
 @field:CPF(message = "Invalid input") val cpf: String,
 @field:NotNull(message = "InvalidInput") val income: BigDecimal,
 @field:Email(message = "Invalid input") val email: String,
 @field:NotEmpty(message = "Invalid input") val password: String,
 @field:NotEmpty(message = "Invalid input") val zipCode: String,
 @field:NotEmpty(message = "Invalid input") val street: String,
) {

    fun toEntity(): Customer = Customer(
    firstName = this.firstName,
    lastName = this.lastName,
    cpf = this.cpf,
    income = this.income,
    email = this.email,
    password = this.password,
    address = Address(
     zipCode = this.zipCode,
     street = this.street
    )
  )
}
