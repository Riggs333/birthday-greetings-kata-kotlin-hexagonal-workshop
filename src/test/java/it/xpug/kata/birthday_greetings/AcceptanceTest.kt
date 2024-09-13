package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import com.google.common.truth.Truth.assertThat
import it.xpug.kata.birthday_greetings.adapter.outbound.EmployeeFileAdapter
import org.junit.jupiter.api.AfterEach
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

        assertThat(mailServer.receivedEmailSize).isEqualTo(1)
        val message = mailServer.receivedEmail.next() as SmtpMessage

        assertThat(message.body).isEqualTo("Happy Birthday, dear John!")
        assertThat(message.getHeaderValue("Subject")).isEqualTo("Happy Birthday!")
        val recipients = message.getHeaderValues("To")
        assertThat(recipients).hasLength(1)
        assertThat(recipients[0]).isEqualTo("john.doe@foobar.com")
    }

    @Test
    @Throws(Exception::class)
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/01/01"), "localhost", NONSTANDARD_PORT)

        assertThat(mailServer.receivedEmailSize).isEqualTo(0)
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}
