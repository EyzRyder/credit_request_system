package me.ez.credit.request.system.exception

data class BusinessException(overrider val message: String?) : RuntimeException(message)
