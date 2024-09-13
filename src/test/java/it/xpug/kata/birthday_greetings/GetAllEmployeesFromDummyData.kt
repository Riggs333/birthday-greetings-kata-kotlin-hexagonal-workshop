package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.application.port.outbound.GetAllEmployeePort

class GetAllEmployeesFromDummyData: GetAllEmployeePort {

    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }

    override fun get(): List<Employee> {
        return employees
    }
}