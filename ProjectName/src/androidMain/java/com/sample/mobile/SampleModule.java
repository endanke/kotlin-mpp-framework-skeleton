package com.sample.mobile;

class SampleModule {

    // The library can include existing Java sources in Android,
    // if they are placed under the Android target folder.
    // Thanks to the language support of Kotlin, they can be directly
    // referenced in the Kotlin source.
    static Integer multiply(Integer a, Integer b) {
        return a * b;
    }

}
