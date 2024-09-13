package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.application.port.outbound.GetAllEmployeePort
import it.xpug.kata.birthday_greetings.application.port.outbound.SendEmailPort

class BirthdayService(
    private val getAllEmployeePort: GetAllEmployeePort,
    private val sendEmailAdapter: SendEmailPort
) {
    fun sendGreetings(xDate: XDate) {

        val employees = getAllEmployeePort.get()

        employees
            .filter { it.isBirthday(xDate) }
            .map {
                val recipient = it.email
                val body = "Happy Birthday, dear %NAME%!".replace("%NAME%", it.firstName!!)
                val subject = "Happy Birthday!"
                Mail(recipient!!, body, subject)
            }.forEach {
                sendEmailAdapter.sendEmail(it)
            }
    }
}

