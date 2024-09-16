package it.xpug.kata.birthday_greetings

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Month

class XDateTest {
    @Test
    fun getters() {
        val date = XDate("1789/01/24")

        assertThat(date.month).isEqualTo(Month.JANUARY.value)
        assertThat(date.day).isEqualTo(24L)
    }

    @Test
    fun isSameDate() {
        val date = XDate("1789/01/24")
        val sameDay = XDate("2001/01/24")
        val notSameDay = XDate("1789/01/25")
        val notSameMonth = XDate("1789/02/25")

        assertThat(date.isSameDay(sameDay)).isTrue()
        assertThat(date.isSameDay(notSameDay)).isFalse()
        assertThat(date.isSameDay(notSameMonth)).isFalse()
    }

    @Test
    fun equality() {
        val base = XDate("2000/01/02")
        val same = XDate("2000/01/02")
        val different = XDate("2000/01/04")

        assertThat(base).isNotNull()
        assertThat(base).isEqualTo(base)
        assertThat(base).isEqualTo(same)
        assertThat(base).isNotEqualTo(different)
    }
}
