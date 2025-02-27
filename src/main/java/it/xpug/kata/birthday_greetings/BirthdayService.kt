package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.ports.EmailService
import it.xpug.kata.birthday_greetings.ports.EmployeeRepository

class BirthdayService(
    private val employeeRepository: EmployeeRepository,
    private val emailService: EmailService
) {
    fun sendGreetings(xDate: XDate) {
        employeeRepository.findEmployees()
            .filter { it.isBirthday(xDate) }
            .forEach { employee ->
                val body = "Happy Birthday, dear ${employee.firstName}!"
                emailService.sendBirthdayGreeting(
                    to = employee.email!!,
                    subject = "Happy Birthday!",
                    body = body
                )
            }
    }
}
