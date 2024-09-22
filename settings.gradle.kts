pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        google()
        maven {
            url = uri("https://android-sdk.is.com/")
        }
        maven { url = uri("https://jitpack.io") }
        maven {
            url =
                uri("https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea")
        }
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://android-sdk.is.com/") }
        maven {
            url =
                uri("https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea")
        }
        maven { url = uri("https://sdk.tapjoy.com/") }
        maven { url = uri("https://artifact.bytedance.com/repository/pangle/") }
        maven {
            url = uri("https://maven.pkg.jetbrains.space/keego/p/haki/haki")
            credentials {
                username = "keego"
                password = "keego"
            }
        }
    }
}

rootProject.name = "Base"
include(":app")
include(":flow-preferences")
include(":lazy-preference")
include(":base")
include(":multi-theme")
