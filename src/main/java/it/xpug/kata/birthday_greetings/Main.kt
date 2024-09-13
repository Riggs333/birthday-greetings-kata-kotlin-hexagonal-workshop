package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.adapter.inbound.CliGreetEmployeesAdapter
import it.xpug.kata.birthday_greetings.adapter.outbound.GetAllEmployeeFromFileAdapter
import it.xpug.kata.birthday_greetings.adapter.outbound.SendEmailAdapter

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val getAllEmployeePort = GetAllEmployeeFromFileAdapter("employee_data.txt")
        val emailAdapter = SendEmailAdapter("localhost", 25)
        val service = BirthdayService(getAllEmployeePort, emailAdapter)
        val adapter = CliGreetEmployeesAdapter(service)
        adapter.greetEmployees(args)
    }
}
