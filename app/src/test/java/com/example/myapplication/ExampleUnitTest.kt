package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testCalculateCost_Taxi() {
        val calcActivity = CalcActivity()

        val result = calcActivity.calculateCost(1000, "Такси")

        assertEquals(1000.0, result, 0.0)
    }

    @Test
    fun testCalculateCost_Minibus() {
        val calcActivity = CalcActivity()

        val result = calcActivity.calculateCost(1000, "Микроавтобус")

        assertEquals(1500.0, result, 0.0)
    }

    @Test
    fun testCalculateCost_Bus() {
        val calcActivity = CalcActivity()

        val result = calcActivity.calculateCost(1000, "Автобус")

        assertEquals(2000.0, result, 0.0)
    }

    @Test
    fun testCalculateCost_InvalidMode() {

        val calcActivity = CalcActivity()

        val result = calcActivity.calculateCost(1000, "Неизвестный")

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun testCalculateCost_ZeroCost() {
        val calcActivity = CalcActivity()

        val result = calcActivity.calculateCost(0, "Такси")

        assertEquals(0.0, result, 0.0)
    }
}
