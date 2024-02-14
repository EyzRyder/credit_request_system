package me.ez.credit.request.system.dto

import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalData

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstOfInstallment: LocalData,
    val numberOfInsdtallments: Int,
    val customerId: Long,
){
    fun toEntity(): Credit = Credit(
    creditValue = this.creditValue,
    dayFirstOfInstallment = this.dayFirstOfInstallment,
    numberOfInsdtallments = this.numberOfInsdtallments,
    customer = Customer(id = this.customerId)
    )
}
