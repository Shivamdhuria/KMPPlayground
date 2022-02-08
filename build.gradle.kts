@Suppress("AndroidGradlePluginVersion") buildscript {

    val kotlinVersion: String by project
    println(kotlinVersion)

    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }

//    val kotlinVersion = "1.6.10"
    val sqlDelightVersion: String by project
    val hilt_version = "2.38.1"

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
//        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath( "com.google.dagger:hilt-android-gradle-plugin:$hilt_version")

        with(Deps.Gradle) {
            classpath(sqlDelight)
            classpath(shadow)
            classpath(gradleVersionsPlugin)
            classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:${Versions.kmpNativeCoroutinesVersion}")
        }
    }
}

group = "me.abc"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
    }

}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
