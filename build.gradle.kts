// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.gradle.api.tasks.GradleBuild

buildscript {
    val kotlin_version = "1.3.60"

    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.2")
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

tasks.register<Delete>("clean") {
    delete.add("ProjectName/build")
}