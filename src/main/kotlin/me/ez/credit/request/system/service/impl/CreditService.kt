package me.ez.credit.request.system.service.impl

import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.repository.CreditRepository
import me.ez.credit.request.system.service.ICreditService
import me.ez.credit.request.system.service.impl.CustomerService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService{

    override fun save (credit: Credit): Credit {
        credit.apply{
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer (customerId: Long): List<Credit> =
      this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode (customerId: Long, creditCode: UUID): Credit {
        val credit: Credt = (this.creditRepository.findByCreditCode(creditCode)
        ?: throw RuntimeException("Creditcode $creditCode not Found"))
        return if (credit.customer?.id ==customerId) credit else throw RuntimeException("Contact Admin")
    }
}
