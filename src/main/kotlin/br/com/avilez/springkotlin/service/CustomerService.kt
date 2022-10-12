package br.com.avilez.springkotlin.service

import org.springframework.stereotype.Service

@Service
class CustomerService : ICustomerService {
    override fun sayHello(): String {
        return "Hello"
    }
}