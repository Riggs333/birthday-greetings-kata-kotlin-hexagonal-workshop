package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.adapter.outbound.EmployeeFileAdapter

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val service = BirthdayService(EmployeeFileAdapter())
        val date = args[0]
        service.sendGreetings("employee_data.txt", XDate(date), "localhost", 25)
    }
}
