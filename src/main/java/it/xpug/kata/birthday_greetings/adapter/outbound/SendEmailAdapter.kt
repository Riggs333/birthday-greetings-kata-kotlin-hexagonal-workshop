package it.xpug.kata.birthday_greetings.adapter.outbound

import it.xpug.kata.birthday_greetings.Mail
import it.xpug.kata.birthday_greetings.application.port.outbound.SendEmailPort
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SendEmailAdapter(val smtpHost: String, val smtpPort: Int): SendEmailPort {
    override fun sendEmail(mail: Mail) {
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        val session = Session.getInstance(props, null)
        // Construct the message
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress("sender@here.com"))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(mail.recipient))
        msg.subject = mail.subject
        msg.setText(mail.body)
        // Send the message
        Thread.sleep(2000L) // simulate slow dependency
        Transport.send(msg)
    }
}