package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AcceptanceTest {
    private lateinit var birthdayService: BirthdayService
    private lateinit var mailServer: SimpleSmtpServer

    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
        birthdayService = BirthdayService()
    }

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    @Throws(Exception::class)
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/10/08"), "localhost", NONSTANDARD_PORT)

        Assert.assertEquals("message not sent?", 1, mailServer.receivedEmailSize.toLong())
        val message = mailServer.receivedEmail.next() as SmtpMessage
        Assert.assertEquals("Happy Birthday, dear John!", message.body)
        Assert.assertEquals("Happy Birthday!", message.getHeaderValue("Subject"))
        val recipients = message.getHeaderValues("To")
        Assert.assertEquals(1, recipients.size.toLong())
        Assert.assertEquals("john.doe@foobar.com", recipients[0].toString())
    }

    @Test
    @Throws(Exception::class)
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/01/01"), "localhost", NONSTANDARD_PORT)

        Assert.assertEquals("what? messages?", 0, mailServer.receivedEmailSize.toLong())
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}
