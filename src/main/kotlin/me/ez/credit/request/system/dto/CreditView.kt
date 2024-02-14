package me.ez.credit.request.system.dto

import java.math.BigDecimal
import java.util.*
import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.enummeration.Status

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int,
    val status: Status,
    val emailCustomer: String,
    val incomeCustomer: BigDecimal
){
    constructor(credit: Credit): this(
    creditCode = credit.creditCode,
    creditValue = credit.creditValue,
    numberOfInstallment = credit.numberOfInstallment,
    status = credit.status,
    emailCustomer = credit.customer?.email,
    incomeCustomer = credit.customer?.income
    )
}
