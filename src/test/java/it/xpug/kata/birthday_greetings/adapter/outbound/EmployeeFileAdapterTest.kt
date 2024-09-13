package it.xpug.kata.birthday_greetings.adapter.outbound

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmployeeFileAdapterTest {
    @Test
    fun `it should read employees from file`() {
        val employeeFileAdapter = EmployeeFileAdapter("employee_data.txt")

        val employees = employeeFileAdapter.getEmployees()

        assertThat(employees).hasSize(2)

        assertThat(employees[0].firstName).isEqualTo("John")
        assertThat(employees[0].lastName).isEqualTo("Doe")

        assertThat(employees[1].firstName).isEqualTo("Mary")
        assertThat(employees[1].lastName).isEqualTo("Ann")
    }
}