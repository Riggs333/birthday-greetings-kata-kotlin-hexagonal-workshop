package it.xpug.kata.birthday_greetings.adapter.outbound

import it.xpug.kata.birthday_greetings.Employee
import org.junit.Assert
import org.junit.Test
import java.io.FileNotFoundException

class GetAllEmployeeFromFileAdapterTest  {
    @Test
    fun `should return all Employees`() {
        val adapter = GetAllEmployeeFromFileAdapter("employee_data.txt")
        val employees = adapter.get()
        val expectedEmployee1 = Employee("John", "Doe" , "1982/10/08", "john.doe@foobar.com")
        val expectedEmployee2 = Employee("Mary", "Ann" , "1975/03/11", "mary.ann@foobar.com")
        Assert.assertEquals(employees.size, 2)
        Assert.assertTrue(employees.contains(expectedEmployee1))
        Assert.assertTrue(employees.contains(expectedEmployee2))
    }

    @Test
    fun `should throw with invalid file name`() {
        val adapter = GetAllEmployeeFromFileAdapter("foo_bar.txt")

        Assert.assertThrows(FileNotFoundException::class.java) {
            adapter.get()
        }
    }
}