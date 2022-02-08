pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "kmp"


include(":library")
include(":android")
include(":compose-desktop")

