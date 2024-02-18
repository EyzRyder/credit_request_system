package me.ez.credit.request.system.service

import me.ez.credit.request.system.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save (credit: Credit): Credit
    fun findAllByCustomer (customerId: Long): List<Credit>
    fun findByCreditCode (customerId: Long, creditCode: UUID): Credit
}
