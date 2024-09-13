package it.xpug.kata.birthday_greetings

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmployeeTest {
    @Test
    @Throws(Exception::class)
    fun testBirthday() {
        val employee = Employee("foo", "bar", "1990/01/31", "a@b.c")

        assertThat(employee.isBirthday(XDate("2008/01/30"))).isFalse()
        assertThat(employee.isBirthday(XDate("2008/01/31"))).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun equality() {
        val base = Employee("First", "Last", "1999/09/01", "first@last.com")
        val same = Employee("First", "Last", "1999/09/01", "first@last.com")
        val different = Employee("First", "Last", "1999/09/01", "boom@boom.com")

        assertThat(base).isNotNull()
        assertThat(base).isEqualTo(same)
        assertThat(base).isNotEqualTo(different)
    }
}
