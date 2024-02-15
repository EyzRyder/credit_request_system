package me.ez.credit.request.system.exception

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.MethodArgumentNotValidException
import java.yime.LocalDateTime

@RestControllerAdvice
class RestException{

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidxception(ex: MethodArgumentNotValidException): Response<ExceptionDetails>{
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach {
            erro: ObjectError ->
            val fieldName: String = (erro as FieldError).field
            val messageError: String? = erro.defaultMessage
            erros[fieldName] = messageError'
        }
        return ResponseEntity(
            ExceptionDetails(
            title = "Bad Request! Consult the Documentation",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.toString(),
            details = erros
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessExcpetion::class)
    fun handlerValidxception(ex: DataAccessExcpetion): Response<ExceptionDetails>{
        return ResponseEntity(
            ExceptionDetails(
            title = "Conflict! Consult the Documentation",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.CONFLICT.value(),
            exception = ex.javaClass.toString(),
            details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.CONFLICT
        )
    }
}
