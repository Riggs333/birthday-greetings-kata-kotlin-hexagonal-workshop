package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.adapter.outbound.EmployeeFileAdapter
import java.util.*
import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage

class BirthdayService(val employeeFileAdapter: EmployeeFileAdapter) {
    fun sendGreetings(xDate: XDate, smtpHost: String, smtpPort: Int) {
        employeeFileAdapter.getEmployees().forEach { employee ->
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.firstName!!)
                val subject = "Happy Birthday!"
                // Create a mail session
                val props = Properties()
                props["mail.smtp.host"] = smtpHost
                props["mail.smtp.port"] = "" + smtpPort
                val session = Session.getInstance(props, null)
                // Construct the message
                val msg: Message = MimeMessage(session)
                msg.setFrom(InternetAddress("sender@here.com"))
                msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient!!))
                msg.subject = subject
                msg.setText(body)
                // Send the message
                Thread.sleep(2000L) // simulate slow dependency
                Transport.send(msg)
            }
        }
    }

}
