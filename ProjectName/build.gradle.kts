import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

val ideaActive = System.getProperty("idea.active") == "true"

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
}

val customFrameworksDir = file("${projectDir}/../native/PlatformLib").absolutePath
val customFramework = "PlatformLib"
val targetName = project.findProperty("IOS_TARGET") ?: "iosArm64"

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(18)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDir("src/androidMain/java")
        }
    }
}

kotlin {
    android()

    val iosArm64 = iosArm64("iosArm64")
    val iosX64 = iosX64("iosX64")
    if (ideaActive) {
        iosX64("ios")
    }

    val frameworkName = "ProjectName"

    project.configure(listOf(iosArm64, iosX64)) {
        binaries.framework {
            embedBitcode("disable")
            baseName = frameworkName
        }

        binaries.all {
            linkerOpts("-F${customFrameworksDir}", "-framework", "PlatformLib")
        }

        compilations["main"].apply {
            cinterops {
                val PlatformLib by cinterops.creating {
                    defFile(project.file("PlatformLib.def"))
                    compilerOpts("-F${customFrameworksDir} -fmodules -framework PlatformLib")
                    includeDirs("${customFrameworksDir}/PlatformLib.framework/Headers")
                }
            }
        }
    }

    project.tasks.register<FatFrameworkTask>("debugFatFramework") {
        baseName = frameworkName
        group = "Universal framework"
        destinationDir = buildDir.resolve("xcode-frameworks")
        from(iosArm64.binaries.getFramework("DEBUG"), iosX64.binaries.getFramework("DEBUG"))
        finalizedBy("packForXCode")
    }

    project.tasks.register<FatFrameworkTask>("releaseFatFramework") {
        baseName = frameworkName
        group = "Universal framework"
        destinationDir = buildDir.resolve("xcode-frameworks")
        from(iosArm64.binaries.getFramework("RELEASE"), iosX64.binaries.getFramework("RELEASE"))
        finalizedBy("packForXCode")
    }

    sourceSets["commonMain"].dependencies {
        api("org.jetbrains.kotlin:kotlin-stdlib-common")
    }

    sourceSets["androidMain"].dependencies {
        api("org.jetbrains.kotlin:kotlin-stdlib-common")
    }

    sourceSets {
        val iosMain = if (ideaActive) getByName("iosMain") else create("iosMain")
        iosMain.dependencies {
            api("org.jetbrains.kotlin:kotlin-stdlib")
        }
        val iosArm64Main by getting
        val iosX64Main by getting
        configure(listOf(iosArm64Main, iosX64Main)) {
            dependsOn(iosMain)
        }
    }
}

tasks.register<Copy>("packForXCode") {
    val frameworkDir = file("${buildDir}/xcode-frameworks")

    into(frameworkDir)
    // Copy embedded framework into the output framework
    into("/${project.name}.framework/Frameworks/${customFramework}.framework") {
        from("${customFrameworksDir}/${customFramework}.framework")
    }

    doLast {
        // Removes the headers from the custom folder to hide the implementation.
        delete("${frameworkDir}/${project.name}.framework/Frameworks/${customFramework}.framework/Headers")
        delete("${frameworkDir}/${project.name}_${targetName}.framework/Frameworks/${customFramework}.framework/Headers")
    }
}

// Copy outputs to the same directory
tasks.register<Copy>("copyArtifacts") {
    doLast {
        copy {
            from("${projectDir}/build/outputs/aar/${project.name}-release.aar")
            into("${projectDir}/outputs")
            rename ("${project.name}-release.aar", "${project.name}.aar")
        }
        copy {
            from("${projectDir}/build/xcode-frameworks/${project.name}.framework")
            into("${projectDir}/outputs/${project.name}.framework")
        }
    }
}

// Task dependencies
tasks.getByName("build") {
    dependsOn ("packForXCode")
    finalizedBy("copyArtifacts")
}