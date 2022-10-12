package br.com.avilez.springkotlin.model

import org.jetbrains.annotations.NotNull
import org.springframework.lang.Nullable
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Customer(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotNull
        val name: String = "",

        @get: NotNull
        val age: Int = 0,

        @get: Nullable
        val birth: LocalDate? = null
)