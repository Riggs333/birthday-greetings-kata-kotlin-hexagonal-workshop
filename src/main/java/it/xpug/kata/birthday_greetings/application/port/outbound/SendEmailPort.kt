package it.xpug.kata.birthday_greetings.application.port.outbound

import it.xpug.kata.birthday_greetings.Mail

interface SendEmailPort {
    fun sendEmail(mail: Mail)
}