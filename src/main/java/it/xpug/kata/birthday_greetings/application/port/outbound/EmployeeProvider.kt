package it.xpug.kata.birthday_greetings.application.port.outbound

import it.xpug.kata.birthday_greetings.Employee

fun interface EmployeeProvider {

    fun getEmployees(): List<Employee>
}