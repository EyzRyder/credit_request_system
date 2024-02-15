package me.ez.credit.request.system.dto

import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalData

data class CreditDto(
    @field:NotEmpty(message = "Invalid input") val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalData,
    @field:NotEmpty(message = "Invalid input") val numberOfInsdtallments: Int,
    @field:NotEmpty(message = "Invalid input") val customerId: Long,
){
    fun toEntity(): Credit = Credit(
    creditValue = this.creditValue,
    dayFirstOfInstallment = this.dayFirstOfInstallment,
    numberOfInsdtallments = this.numberOfInsdtallments,
    customer = Customer(id = this.customerId)
    )
}
