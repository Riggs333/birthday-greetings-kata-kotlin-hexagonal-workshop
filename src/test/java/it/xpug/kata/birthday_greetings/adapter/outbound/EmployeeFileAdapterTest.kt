package it.xpug.kata.birthday_greetings.adapter.outbound

import org.junit.Assert
import org.junit.Test

class EmployeeFileAdapterTest {

    @Test
    fun `should load employees from file`(){
        val adapter = EmployeeFileAdapter("employee_data.txt")

        val employees = adapter.loadEmployees()

        Assert.assertEquals(employees.size, 2)
        Assert.assertEquals(employees[0].firstName, "John")
        Assert.assertEquals(employees[0].lastName, "Doe")

        Assert.assertEquals(employees[1].firstName, "Mary")
        Assert.assertEquals(employees[1].lastName, "Ann")
    }
}