package com.sample.mobile

import com.platformlib.PlatformLib
import com.platformlib.Testing

class IOSSpecific {

    fun sayHello(): String {
        return "Hello from Kotlin, " + PlatformLib().test() + ", " + Testing().test()
    }

}