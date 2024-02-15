package me.ez.credit.request.system.controller

import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.service.CreditService
import me.ez.credit.request.system.dto.CreditDto
import me.ez.credit.request.system.dto.CreditViewList
import me.ez.credit.request.system.dto.CreditView
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/credit")
class CreditResource(
    private val creditService: CreditService
){

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpsStatus.CREATED)
        .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} Saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long):
    ResponseEntity<List<CreditViewList>>{
        val creditViewlist:List<CreditViewList> = this.creditService
        .findAllByCustomerId(customerId).stream()
        .map {credit: Credit -> CreditViewList(credit)}
        .collect(Collectors.toList())

        return ResponseEntity.status(HttpsStatus.OK).body(creditViewlist)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long
    @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId,creditCode)
        return ResponseEntity.status(HttpsStatus.OK).body(CreditView(credit))
    }
}
