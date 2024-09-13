package it.xpug.kata.birthday_greetings.adapter

import it.xpug.kata.birthday_greetings.Employee
import it.xpug.kata.birthday_greetings.application.port.outbound.EmployeeLoadingPort
import java.io.BufferedReader
import java.io.FileReader

class EmployeeFileAdapterMock(private val employeeList: List<Employee>): EmployeeLoadingPort {
    override fun loadEmployees(): List<Employee> = employeeList
}