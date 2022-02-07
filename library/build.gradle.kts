plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    id("com.android.library")
    id("com.squareup.sqldelight")

    id("kotlin-android-extensions")
}

group = "me.abc"
version = "1.0-SNAPSHOT"

kotlin {
    android()

//    listOf(
//        iosX64(),
//        iosArm64(),
//        //iosSimulatorArm64() sure all ios dependencies support this target
//    ).forEach {
//        it.binaries.framework {
//            baseName = "library"
//        }
//    }

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
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }

//        val iosX64Main by getting
//        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
//        val iosMain by getting {
//
//            dependencies {
//                implementation("io.ktor:ktor-client-ios:$ktorVersion")
//                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
//            }
//            //iosSimulatorArm64Main.dependsOn(this)
//        }

        sourceSets["iOSMain"].dependencies {
            implementation("io.ktor:ktor-client-ios:$ktorVersion")
            implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        //val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            //iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.abc.library.shared.cache"
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