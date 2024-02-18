package me.ez.credit.request.system.dto.response

import java.math.BigDecimal
import java.util.*
import me.ez.credit.request.system.entity.Credit

data class CreditViewList(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int
){
    constructor(credit: Credit): this(
    creditCode = credit.creditCode,
    creditValue = credit.creditValue,
    numberOfInstallment = credit.numberOfInstallment
    )
}
