package com.android.dvtweatherapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.dvtweatherapp.data.mappers.toForecastDisplayDataList
import com.android.dvtweatherapp.data.mappers.toWeatherDisplayData
import com.android.dvtweatherapp.data.remote.ForecastDto
import com.android.dvtweatherapp.data.remote.WeatherApi
import com.android.dvtweatherapp.data.remote.WeatherDto
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import com.android.dvtweatherapp.domain.util.Response
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherType
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import java.lang.Exception
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class DefaultWeatherRepositoryTest {
    private val mappersStaticMockPath = "com.android.dvtweatherapp.data.mappers.WeatherDataMappersKt"

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val coroutineDispatcher: TestDispatcher =
        StandardTestDispatcher(TestCoroutineScheduler())

    @RelaxedMockK
    lateinit var weatherApi: WeatherApi

    private lateinit var repository: WeatherRepository

    private val mockLatitude = 24.55
    private val mockLongitude = 48.22

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(coroutineDispatcher)
        mockkStatic(mappersStaticMockPath)
        repository = DefaultWeatherRepository(coroutineDispatcher, weatherApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test if current weather data is successful then return success data`() = runTest {
        val expectedCurrentTemperature = 18F
        val expectedMinTemperature = 14F
        val expectedMaxTemperature = 19F
        val expectedWeatherDto = mockk<WeatherDto>(relaxed = true)
        val expectedResult = mockk<WeatherDisplayData>()

        coEvery { weatherApi.getCurrentWeatherData(any(), any(), any()) } returns expectedWeatherDto

        every { any<WeatherDto>().toWeatherDisplayData() } returns expectedResult
        every { expectedResult.weatherType } returns WeatherType.CLEAR
        every { expectedResult.currentTemperature } returns expectedCurrentTemperature
        every { expectedResult.minTemperature } returns expectedMinTemperature
        every { expectedResult.maxTemperature } returns expectedMaxTemperature

        val actualResult = repository.getCurrentWeatherData(mockLatitude, mockLongitude)
        assertEquals(expectedResult, actualResult.data)
    }

    @Test
    fun `test if error occurred during current weather data fetching then return error response`() =
        runTest {
            val expectedExceptionMessage = "A test error occurred"
            val expectedResult = Response.Error<WeatherDisplayData>(expectedExceptionMessage)
            coEvery { weatherApi.getCurrentWeatherData(any(), any(), any()) } throws Exception(
                expectedExceptionMessage
            )

            val actualResult = repository.getCurrentWeatherData(mockLatitude, mockLongitude)
            assertEquals(expectedResult.message, actualResult.message)
        }

    @Test
    fun `test if forecast weather data is successful then return success data`() = runTest {
        val mockList: List<ForecastDisplayData> =
            listOf(mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true))
        val expectedForecastDto = mockk<ForecastDto>(relaxed = true)

        coEvery { weatherApi.getForecastWeatherData(any(), any(), any()) } returns expectedForecastDto
        every { any<ForecastDto>().toForecastDisplayDataList() } returns mockList

        val actualResult = repository.getForecastWeatherData(mockLatitude, mockLongitude)
        assertEquals(mockList, actualResult.data)
    }

    @Test
    fun `test if error occurred during forecast weather data fetching then return error response`() =
        runTest {
            val expectedExceptionMessage = "Forecast data not available"
            val expectedResult = Response.Error<WeatherDisplayData>(expectedExceptionMessage)
            coEvery { weatherApi.getForecastWeatherData(any(), any(), any()) } throws Exception(
                expectedExceptionMessage
            )

            val actualResult = repository.getForecastWeatherData(mockLatitude, mockLongitude)
            assertEquals(expectedResult.message, actualResult.message)
        }
}
