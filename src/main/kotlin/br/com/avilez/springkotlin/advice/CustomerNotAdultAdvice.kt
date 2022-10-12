package br.com.avilez.springkotlin.advice

import br.com.avilez.springkotlin.exception.CustomerNotAdultException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CustomerNotAdultAdvice {

    @ResponseBody
    @ExceptionHandler(value = [(CustomerNotAdultException::class)])
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun customerNotAdultHandler(ex: CustomerNotAdultException): String? {
        return ex.message
    }
}