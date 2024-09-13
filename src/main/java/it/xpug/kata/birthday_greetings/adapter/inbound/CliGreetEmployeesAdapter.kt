package it.xpug.kata.birthday_greetings.adapter.inbound

import it.xpug.kata.birthday_greetings.BirthdayService
import it.xpug.kata.birthday_greetings.XDate

class CliGreetEmployeesAdapter(private val birthdayService: BirthdayService) {
    fun greetEmployees(args: Array<String>) {
        val date = args.get(0)
        birthdayService.sendGreetings(XDate(date))
    }
}