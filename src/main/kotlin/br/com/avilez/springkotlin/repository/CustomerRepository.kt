package br.com.avilez.springkotlin.repository

import br.com.avilez.springkotlin.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
}