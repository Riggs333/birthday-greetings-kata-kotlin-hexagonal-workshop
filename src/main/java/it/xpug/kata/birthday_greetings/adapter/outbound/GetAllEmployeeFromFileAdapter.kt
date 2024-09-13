package it.xpug.kata.birthday_greetings.adapter.outbound

import it.xpug.kata.birthday_greetings.Employee
import it.xpug.kata.birthday_greetings.application.port.outbound.GetAllEmployeePort
import java.io.BufferedReader
import java.io.FileReader

class GetAllEmployeeFromFileAdapter(private val fileName: String = ""): GetAllEmployeePort {
    override fun get(): List<Employee> {
        BufferedReader(FileReader(fileName)).use { reader ->
            reader.readLine() // skip header
            var str: String

            val employees = mutableListOf<Employee>()

            while ((reader.readLine().also { str = it }) != null) {
                val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
                employees.add(employee)
            }

            return employees
        }
    }
}