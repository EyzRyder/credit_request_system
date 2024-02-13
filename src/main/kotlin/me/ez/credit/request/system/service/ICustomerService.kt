package me.ez.credit.request.system

import me.ez.credit.request.system.Customer
import java.util.UUID

interface ICustomerService {

    fun save (customer: Customer): Customer

    fun findById (id: Long): Customer

    fun delete (id: Long): Customer
}
