package me.ez.credit.request.system.controller

import org.springframework.web.bind.annotation.*
import me.ez.credit.request.system.entity.Customer
import me.ez.credit.request.system.dto.CustomerDto
import me.ez.credit.request.system.service.impl.CustomerService
import me.ez.credit.request.system.controller.CustomerView
import me.ez.credit.request.system.controller.CustomerUpdateDto

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${saveCustomer.email} saved!")
    }

    @GetMepping{"/{id}"}
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
    @RequestBody customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {
       val customer: Customer =  this.customerService.findById(id)
       val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
       val customerUpdated: Customer = this.customerService.save(customerToUpdate)
       return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}
