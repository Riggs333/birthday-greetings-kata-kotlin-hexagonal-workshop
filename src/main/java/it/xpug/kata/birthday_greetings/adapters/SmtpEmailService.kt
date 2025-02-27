package it.xpug.kata.birthday_greetings.adapters

import it.xpug.kata.birthday_greetings.ports.EmailService
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SmtpEmailService(
    private val smtpHost: String,
    private val smtpPort: Int
) : EmailService {
    
    override fun sendBirthdayGreeting(to: String, subject: String, body: String) {
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = smtpPort.toString()
        
        val session = Session.getInstance(props, null)
        val msg: Message = MimeMessage(session)
        
        msg.setFrom(InternetAddress("sender@here.com"))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(to))
        msg.subject = subject
        msg.setText(body)
        
        // Simulate slow dependency
        Thread.sleep(2000L)
        Transport.send(msg)
    }
} 