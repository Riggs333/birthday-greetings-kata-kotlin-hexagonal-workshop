package it.xpug.kata.birthday_greetings.application.port.outbound

import it.xpug.kata.birthday_greetings.Employee

interface GetAllEmployeePort {
    fun get(): List<Employee>
}