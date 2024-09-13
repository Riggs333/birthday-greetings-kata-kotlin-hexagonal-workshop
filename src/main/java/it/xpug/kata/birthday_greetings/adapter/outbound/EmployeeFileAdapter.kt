package it.xpug.kata.birthday_greetings.adapter.outbound

import it.xpug.kata.birthday_greetings.Employee
import it.xpug.kata.birthday_greetings.application.port.outbound.EmployeeLoadingPort
import java.io.BufferedReader
import java.io.FileReader

class EmployeeFileAdapter(private val fileName: String): EmployeeLoadingPort {
    override fun loadEmployees(): List<Employee> {
        val employees: MutableList<Employee> = mutableListOf()

        BufferedReader(FileReader(fileName)).use { reader ->
            reader.readLine() // skip header
            var str: String

            while ((reader.readLine().also { str = it }) != null) {
                val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                employees.add(Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]))
            }
        }

        return employees;
    }
}