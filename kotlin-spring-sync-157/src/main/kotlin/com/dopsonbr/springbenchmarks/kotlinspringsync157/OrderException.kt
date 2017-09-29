package com.dopsonbr.springbenchmarks.kotlinspringsync157

import org.springframework.http.HttpStatus

class OrderException(private val appMessage: String, message: String, cause: Throwable, private val httpStatus: HttpStatus) : Exception(message, cause)
