package it.xpug.kata.birthday_greetings.ports

interface EmailService {
    fun sendBirthdayGreeting(to: String, subject: String, body: String)
} 