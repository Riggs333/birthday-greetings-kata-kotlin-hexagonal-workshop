package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import it.xpug.kata.birthday_greetings.adapter.outbound.EmployeeFileAdapter
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AcceptanceTest {
    private lateinit var birthdayService: BirthdayService
    private lateinit var mailServer: SimpleSmtpServer

    @BeforeEach
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
        birthdayService = BirthdayService(EmployeeFileAdapter("employee_data.txt"))
    }

    @AfterEach
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    @Throws(Exception::class)
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/10/08"), "localhost", NONSTANDARD_PORT)

        assertEquals(
            1,
            mailServer.receivedEmailSize.toLong(),
            "message not sent?"
        )
        val message = mailServer.receivedEmail.next() as SmtpMessage
        assertEquals("Happy Birthday, dear John!", message.body)
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"))
        val recipients = message.getHeaderValues("To")
        assertEquals(1, recipients.size.toLong())
        assertEquals("john.doe@foobar.com", recipients[0].toString())
    }

    @Test
    @Throws(Exception::class)
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/01/01"), "localhost", NONSTANDARD_PORT)

        assertEquals(
            0,
            mailServer.receivedEmailSize.toLong(),
            "what? messages?"
        )
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}
