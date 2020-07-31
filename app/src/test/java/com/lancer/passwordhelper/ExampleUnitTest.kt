package com.lancer.passwordhelper

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("start")
        val job = GlobalScope.launch() {
            delay(1000)
            println("hello ")
        }
        println("end")

    }
}