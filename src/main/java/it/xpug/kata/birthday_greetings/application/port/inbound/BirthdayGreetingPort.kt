package it.xpug.kata.birthday_greetings.application.port.inbound

import it.xpug.kata.birthday_greetings.XDate

interface BirthdayGreetingPort {
    fun sendGreetings(xDate: XDate, smtpHost: String, smtpPort: Int)
}