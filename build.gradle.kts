buildscript {
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)

//        classpath("com.google.gms:google-services:4.3.14")
//        classpath("com.google.firebase:perf-plugin:1.4.2")
//        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
//        classpath("com.google.firebase:firebase-appdistribution-gradle:3.2.0")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.22" apply false
    alias(libs.plugins.android.library) apply false
}
