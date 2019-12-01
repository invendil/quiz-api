package com.shkila.quizapi.service

interface ServiceInterface {
    fun <T> findById(id: Long?): T
    fun removeById(id: Long?)
}
