package it.xpug.kata.birthday_greetings.adapter.inbound

import it.xpug.kata.birthday_greetings.XDate
import it.xpug.kata.birthday_greetings.application.port.inbound.BirthdayGreetingPort

class GruessOnkelAdapter(private val birthdayGreetingPort: BirthdayGreetingPort) {
    fun doIt(xDate: XDate, smtpHost: String, smtpPort: Int) {
        birthdayGreetingPort.sendGreetings(xDate, smtpHost, smtpPort)
    }
}