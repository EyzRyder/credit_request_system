package me.ez.credit.request.system.dto.request

import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalData
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class CreditDto(
    @field:NotEmpty(message = "Invalid input") val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalData,
    @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallments: Int,
    @field:NotEmpty(message = "Invalid input") val customerId: Long,
){
    fun toEntity(): Credit = Credit(
    creditValue = this.creditValue,
    dayFirstOfInstallment = this.dayFirstOfInstallment,
    numberOfInsdtallments = this.numberOfInsdtallments,
    customer = Customer(id = this.customerId)
    )
}
