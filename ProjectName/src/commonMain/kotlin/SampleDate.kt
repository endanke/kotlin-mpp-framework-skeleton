package com.sample.mobile

// A very basic example of the expect and actual mechanism
// https://kotlinlang.org/docs/reference/platform-specific-declarations.html
expect class SampleDate() {
    val timestamp: Long
}