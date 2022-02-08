plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")

    id("com.squareup.sqldelight")
//    id("com.rickclephas.kmp.nativecoroutines")
    id("kotlin-android-extensions")
}

group = "me.abc"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm()

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "PeopleInSpace"
        homepage = "https://github.com/joreilly/PeopleInSpace"
    }

    val iosTarget: (String, org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit) -> org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64 // available to KT 1.5.30
        else -> ::iosX64
    }
    iosTarget("iOS") {}


    val ktorVersion = "1.6.7"
    val serializationVersion = "1.3.2"
    val sqlDelightVersion: String by project
    val coroutinesVersion = "1.3.9-native-mt"

    sourceSets {

//        sourceSets["commonMain"].dependencies {
//            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
//            implementation("io.ktor:ktor-client-core:$ktorVersion")
//            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
//            implementation("io.ktor:ktor-client-serialization:$ktorVersion")
//            implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
//            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
//
//        }
//
//        sourceSets["androidMain"].dependencies {
//            implementation("io.ktor:ktor-client-android:$ktorVersion")
//            implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
//        }
//
//        sourceSets["iOSMain"].dependencies {
//            implementation("io.ktor:ktor-client-ios:$ktorVersion")
//            implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
//        }


        sourceSets["commonMain"].dependencies {

            with(Deps.Ktor) {
                implementation(clientCore)
                implementation(clientJson)
                implementation(clientLogging)
                implementation(contentNegotiation)
                implementation(json)
            }

            with(Deps.Kotlinx) {
                implementation(coroutinesCore)
                implementation(serializationCore)
            }

            with(Deps.SqlDelight) {
                implementation(runtime)
                implementation(coroutineExtensions)
            }

            with(Deps.Koin) {
                api(core)
                api(test)
            }

            with(Deps.Log) {
                api(kermit)
            }
        }


        sourceSets["androidMain"].dependencies {
            implementation(Deps.Ktor.clientAndroid)
            implementation(Deps.SqlDelight.androidDriver)
        }

        sourceSets["jvmMain"].dependencies {
            implementation(Deps.Ktor.clientJava)
            implementation(Deps.SqlDelight.sqliteDriver)
            implementation(Deps.Log.slf4j)
        }
        sourceSets["iOSMain"].dependencies {
            implementation(Deps.Ktor.clientIos)
            implementation(Deps.SqlDelight.nativeDriver)
        }
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.elixer.paws.library.cache"
    }
}
android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 31
    }
}
//multiplatformSwiftPackage {
//    packageName("PeopleInSpace")
//    swiftToolsVersion("5.3")
//    targetPlatforms {
//        iOS { v("13") }
//    }
//}