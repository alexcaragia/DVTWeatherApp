package com.android.dvtweatherapp.data.mappers

import io.mockk.MockKAnnotations
import io.mockk.mockkStatic
import kotlin.test.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DateFormatterTest(
    private val inputTimestamp: Long,
    private val formattedDate: String
) {
    private val mappersStaticMockPath =
        "com.android.dvtweatherapp.data.mappers.WeatherDataMappersKt"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(mappersStaticMockPath)
    }

    @Test
    fun `test date formatter`() {
        assertEquals(formattedDate, inputTimestamp.formatDate())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): Collection<Array<Any>> {
            return arrayListOf(
                arrayOf(1666623600, "Monday"),
                arrayOf(1666699200, "Tuesday"),
                arrayOf(1666796400, "Wednesday"),
                arrayOf(1666828800, "Thursday"),
                arrayOf(1666915200, "Friday"),
                arrayOf(1667044800, "Saturday")
            )
        }
    }
}