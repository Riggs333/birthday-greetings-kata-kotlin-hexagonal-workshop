package it.xpug.kata.birthday_greetings.adapters

import it.xpug.kata.birthday_greetings.Employee
import it.xpug.kata.birthday_greetings.ports.EmployeeRepository
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

class FileEmployeeRepository(private val filePath: String) : EmployeeRepository {
    
    companion object {
        private const val EXPECTED_HEADER = "last_name, first_name, date_of_birth, email"
    }
    
    override fun findEmployees(): List<Employee> {
        val file = File(filePath)
        if (!file.exists()) {
            throw IllegalArgumentException("File does not exist: $filePath")
        }
        
        try {
            BufferedReader(FileReader(file)).use { reader ->
                val header = reader.readLine()?.trim() ?: throw IllegalArgumentException("File is empty")
                
                if (header != EXPECTED_HEADER) {
                    throw IllegalArgumentException("Invalid file format: Expected header '$EXPECTED_HEADER' but found '$header'")
                }
                
                return reader.lineSequence()
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .map { parseLine(it) }
                    .toList()
            }
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("File not found: $filePath", e)
        } catch (e: Exception) {
            throw IllegalArgumentException("Error reading file: ${e.message}", e)
        }
    }
    
    private fun parseLine(line: String): Employee {
        val fields = line.split(",")
            .map { it.trim() }
            
        if (fields.size != 4) {
            throw IllegalArgumentException("Invalid line format: Expected 4 fields but found ${fields.size}")
        }
        
        val lastName = fields[0]
        val firstName = fields[1]
        val birthDate = fields[2]
        val email = fields[3]
        
        if (birthDate.isEmpty()) {
            throw IllegalArgumentException("Birth date is required")
        }
        
        if (email.isEmpty()) {
            throw IllegalArgumentException("Email is required")
        }
        
        return Employee(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            email = email
        )
    }
} 