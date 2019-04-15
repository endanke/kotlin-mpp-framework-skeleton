package com.sample.mobile

import java.util.*

actual class SampleDate actual constructor() {
    actual val timestamp: Long
        get() = Date().time

}