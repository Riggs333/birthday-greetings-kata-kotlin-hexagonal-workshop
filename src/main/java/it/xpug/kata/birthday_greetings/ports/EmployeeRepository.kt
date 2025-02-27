package it.xpug.kata.birthday_greetings.ports

import it.xpug.kata.birthday_greetings.Employee

interface EmployeeRepository {
    fun findEmployees(): List<Employee>
} 