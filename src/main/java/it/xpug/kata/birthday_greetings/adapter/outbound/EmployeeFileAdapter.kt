package it.xpug.kata.birthday_greetings.adapter.outbound

import it.xpug.kata.birthday_greetings.Employee
import it.xpug.kata.birthday_greetings.application.port.outbound.EmployeeProvider
import java.io.BufferedReader
import java.io.FileReader

class EmployeeFileAdapter(val fileName: String) : EmployeeProvider {
    override fun getEmployees(): List<Employee> {
        val employees = mutableListOf<Employee>()
        BufferedReader(FileReader(fileName)).use { reader ->
            reader.readLine() // skip header
            var str: String

            while ((reader.readLine().also { str = it }) != null) {
                val employeeData =
                    str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val employee =
                    Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
                employees.add(employee)
            }
            return employees.toList()
        }
    }
}