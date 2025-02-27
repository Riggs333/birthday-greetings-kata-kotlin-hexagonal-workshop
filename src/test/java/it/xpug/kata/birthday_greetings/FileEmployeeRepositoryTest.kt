package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.adapters.FileEmployeeRepository
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import kotlin.io.path.createTempFile

class FileEmployeeRepositoryTest {
    
    @Test
    fun `should read employees from file correctly`() {
        // Given
        val tempFile = createTempFile(prefix = "employees_", suffix = ".txt").toFile()
        tempFile.writeText("""
            last_name, first_name, date_of_birth, email
            Doe, John, 1982/10/08, john.doe@foobar.com
            Ann, Mary, 1975/03/11, mary.ann@foobar.com
        """.trimIndent())
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        val employees = repository.findEmployees()
        
        // Then
        assertEquals(2, employees.size)
        
        val john = employees[0]
        assertEquals("John", john.firstName)
        assertEquals("Doe", john.lastName)
        assertEquals("1982/10/08", john.birthDate)
        assertEquals("john.doe@foobar.com", john.email)
        
        val mary = employees[1]
        assertEquals("Mary", mary.firstName)
        assertEquals("Ann", mary.lastName)
        assertEquals("1975/03/11", mary.birthDate)
        assertEquals("mary.ann@foobar.com", mary.email)
    }
    
    @Test
    fun `should handle empty file correctly`() {
        // Given
        val tempFile = createTempFile(prefix = "empty_employees_", suffix = ".txt").toFile()
        tempFile.writeText("last_name, first_name, date_of_birth, email")
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        val employees = repository.findEmployees()
        
        // Then
        assertTrue(employees.isEmpty())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw exception when file does not exist`() {
        // Given
        val nonExistentFile = "non_existent_file.txt"
        val repository = FileEmployeeRepository(nonExistentFile)
        
        // When
        repository.findEmployees()
        
        // Then: should throw IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw exception when header format is invalid`() {
        // Given
        val tempFile = createTempFile(prefix = "invalid_header_", suffix = ".txt").toFile()
        tempFile.writeText("""
            wrong, header, format
            Doe, John, 1982/10/08, john.doe@foobar.com
        """.trimIndent())
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        repository.findEmployees()
        
        // Then: should throw IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw exception when required field is missing`() {
        // Given
        val tempFile = createTempFile(prefix = "missing_field_", suffix = ".txt").toFile()
        tempFile.writeText("""
            last_name, first_name, date_of_birth, email
            Doe, John, , john.doe@foobar.com
        """.trimIndent())
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        repository.findEmployees()
        
        // Then: should throw IllegalArgumentException
    }

    @Test
    fun `should handle whitespace in fields correctly`() {
        // Given
        val tempFile = createTempFile(prefix = "whitespace_", suffix = ".txt").toFile()
        tempFile.writeText("""
            last_name, first_name, date_of_birth, email
            Doe,    John   ,   1982/10/08   ,   john.doe@foobar.com   
        """.trimIndent())
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        val employees = repository.findEmployees()
        
        // Then
        assertEquals(1, employees.size)
        val employee = employees[0]
        assertEquals("John", employee.firstName)
        assertEquals("Doe", employee.lastName)
        assertEquals("1982/10/08", employee.birthDate)
        assertEquals("john.doe@foobar.com", employee.email)
    }

    @Test
    fun `should skip empty lines`() {
        // Given
        val tempFile = createTempFile(prefix = "empty_lines_", suffix = ".txt").toFile()
        tempFile.writeText("""
            last_name, first_name, date_of_birth, email
            
            Doe, John, 1982/10/08, john.doe@foobar.com
            
            Ann, Mary, 1975/03/11, mary.ann@foobar.com
            
        """.trimIndent())
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        val employees = repository.findEmployees()
        
        // Then
        assertEquals(2, employees.size)
    }

    @Test
    fun `should ignore duplicate employee entries`() {
        // Given
        val tempFile = createTempFile(prefix = "duplicates_", suffix = ".txt").toFile()
        tempFile.writeText("""
            last_name, first_name, date_of_birth, email
            Doe, John, 1982/10/08, john.doe@foobar.com
            Doe, John, 1982/10/08, john.doe@foobar.com
            Doe, John, 1982/10/08, john.doe@foobar.com
        """.trimIndent())
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When
        val employees = repository.findEmployees()
        
        // Then
        assertEquals("Should only contain one employee", 1, employees.size)
        
        val employee = employees[0]
        assertEquals("John", employee.firstName)
        assertEquals("Doe", employee.lastName)
        assertEquals("1982/10/08", employee.birthDate)
        assertEquals("john.doe@foobar.com", employee.email)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw exception when file exists but is not readable`() {
        // Given
        val tempFile = createTempFile(prefix = "unreadable_", suffix = ".txt").toFile()
        tempFile.writeText("""
            last_name, first_name, date_of_birth, email
            Doe, John, 1982/10/08, john.doe@foobar.com
        """.trimIndent())
        tempFile.setReadable(false)
        
        val repository = FileEmployeeRepository(tempFile.absolutePath)
        
        // When/Then
        repository.findEmployees()
    }
} 