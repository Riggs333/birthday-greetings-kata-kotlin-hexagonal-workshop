package it.xpug.kata.birthday_greetings.application.port.outbound

import it.xpug.kata.birthday_greetings.Employee

interface EmployeeLoadingPort {
    fun loadEmployees() : List<Employee>
}