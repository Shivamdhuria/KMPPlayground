pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "kmp"


include(":common")
include(":android")
include(":compose-desktop")

