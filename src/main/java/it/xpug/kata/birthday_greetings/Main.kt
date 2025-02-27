package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.adapters.FileEmployeeRepository
import it.xpug.kata.birthday_greetings.adapters.SmtpEmailService

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val employeeRepository = FileEmployeeRepository("employee_data.txt")
        val emailService = SmtpEmailService("localhost", 25)
        val birthdayService = BirthdayService(employeeRepository, emailService)
        
        val date = args[0]
        birthdayService.sendGreetings(XDate(date))
    }
}
