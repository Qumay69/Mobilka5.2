package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.matcher.IntentMatchers
import org.junit.Before

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class AppTest {

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun testNavigateToInputActivity() {
        onView(withId(R.id.buttonInput)).perform(click())

        Intents.intended(IntentMatchers.hasComponent(InputActivity::class.java.name))

        Intents.release()
    }

    @Test
    fun testNavigateToCalcActivity() {

        onView(withId(R.id.buttonCalc)).perform(click())

        Intents.intended(IntentMatchers.hasComponent(CalcActivity::class.java.name))

        Intents.release()
    }

    @Test
    fun testExitApp() {

        onView(withId(R.id.buttonExit)).perform(click())
    }

    @Test
    fun testInputDataAndNavigateToCalcActivity() {

        onView(withId(R.id.editTextCost)).perform(typeText("1000"), closeSoftKeyboard())

        onView(withId(R.id.radioTaxi)).perform(click())

        onView(withId(R.id.buttonSave)).perform(click())

        Intents.intended(IntentMatchers.hasComponent(CalcActivity::class.java.name))
        Intents.intended(IntentMatchers.hasExtra("COST", 1000))
        Intents.intended(IntentMatchers.hasExtra("MODE", "Такси"))

        Intents.release()
    }

    @Test
    fun testInputDataAndShowToastIfInvalid() {

        onView(withId(R.id.editTextCost)).perform(typeText("1000"), closeSoftKeyboard())

        onView(withId(R.id.buttonSave)).perform(click())

    }

    @Test
    fun testDisplayCorrectCalculationInCalcActivity() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), CalcActivity::class.java)
        intent.putExtra("COST", 1000)
        intent.putExtra("MODE", "Такси")
        launchActivity<CalcActivity>(intent)

        onView(withId(R.id.textViewResult))
            .check(matches(withText("Тип транспорта: Такси\nСтоимость аренды: 1000\nРассчитанная стоимость: 1000.0")))
    }

    @Test
    fun testCalculationForMinibus() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), CalcActivity::class.java)
        intent.putExtra("COST", 2000)
        intent.putExtra("MODE", "Микроавтобус")
        launchActivity<CalcActivity>(intent)

        onView(withId(R.id.textViewResult))
            .check(matches(withText("Тип транспорта: Микроавтобус\nСтоимость аренды: 2000\nРассчитанная стоимость: 3000.0")))
    }

    @Test
    fun testCalculationForBus() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), CalcActivity::class.java)
        intent.putExtra("COST", 1500)
        intent.putExtra("MODE", "Автобус")
        launchActivity<CalcActivity>(intent)

        onView(withId(R.id.textViewResult))
            .check(matches(withText("Тип транспорта: Автобус\nСтоимость аренды: 1500\nРассчитанная стоимость: 3000.0")))
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}