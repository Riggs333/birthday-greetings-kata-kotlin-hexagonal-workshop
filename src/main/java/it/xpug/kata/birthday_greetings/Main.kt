package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.adapter.inbound.GruessOnkelAdapter
import it.xpug.kata.birthday_greetings.adapter.outbound.EmployeeFileAdapter

object Main {
    @JvmStatic
    fun main(args: Array<String>) {


        GruessOnkelAdapter()
        val service = BirthdayService(EmployeeFileAdapter("employee_data.txt"))
        val date = args[0]
        service.sendGreetings( XDate(date), "localhost", 25)
    }
}
