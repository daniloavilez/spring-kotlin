package br.com.avilez.springkotlin.controller

import br.com.avilez.springkotlin.configuration.CustomKafkaTemplate
import br.com.avilez.springkotlin.exception.CustomerNotAdultException
import br.com.avilez.springkotlin.model.Customer
import br.com.avilez.springkotlin.repository.CustomerRepository
import br.com.avilez.springkotlin.service.CustomerService
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/customer")
class CustomerController(val service: CustomerService,
                         val customerRepository: CustomerRepository,
                         val kafkaTemplate: KafkaTemplate<Int, String>) {

    var customer = mutableListOf<Customer>(
            Customer(1, "Danilo", 29, LocalDate.parse("1990-09-07")),
            Customer(2, "Amanda", 27, LocalDate.parse("1993-01-31"))
    )

    @GetMapping()
    fun getCustomers() = customerRepository.findAll()


    @GetMapping("{id}")
    fun getCustomer(@PathVariable(value = "id", required = true) id: Long) =
            customer.firstOrNull { it.id == id }

    @PostMapping()
    fun newCustomer(@RequestBody customer: Customer): Boolean {
        if (customer.age < 18) {
            throw CustomerNotAdultException("Customer is ${customer.age}, he or she can't use our services")
        }

        if (customer.name.isBlank() || customer.name.isEmpty()) {
            return false
        }

        kafkaTemplate.send("my-topic", 1, "Hello World")

        return true
    }

    @PutMapping("{id}")
    fun updateCustomer(@PathVariable(value = "id", required = true) id: Long, @RequestBody newCustomer: Customer): Boolean {
        customer.indexOfFirst { it.id == id }.also {
            return when(it) {
                -1 -> false
                else -> {
                    customer[it] = newCustomer
                    true
                }
            }
        }
    }

}