package me.ez.credit.request.system.controller

import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.service.CreditService
import me.ez.credit.request.system.dto.CreditDto
import me.ez.credit.request.system.dto.CreditViewList
import me.ez.credit.request.system.dto.CreditView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/credit")
class CreditResource(
    private val creditService: CreditService
){

    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} Saved!"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewList>{
        return this.creditService.findAllByCustomerId(customerId).stream()
        .map {credit: Credit -> CreditViewList(credit)}
        .collect(Collectors.toList())
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long
    @PathVariable creditCode: UUID): CreditView {
        val credit: Credit = this.creditService.findByCreditCode(customerId,creditCode)
        return CreditView(credit)
    }
}
