package com.sample.mobile

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual class SampleDate actual constructor() {
    actual val timestamp: Long
        get() = NSDate().timeIntervalSince1970.toLong()
}