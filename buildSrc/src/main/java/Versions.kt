object Versions {
    object Android {
        const val BUILD_TOOLS = "29.0.3"
        const val COMPILE_SDK = 28

        object DefaultConfig {
            const val APPLICATION_ID = "com.jaimegc.dragonballmodularization"
            const val APPLICATION_NAME = "dragonball_modularization"
            const val MIN_ANDROID_SDK = 21
            const val TARGET_ANDROID_SDK = 28
            const val VERSION_CODE = 1
            const val VERSION_NAME = "1.0"
            const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
        }

        object BuildTypes {
            const val DEBUG = "debug"
        }
    }

    object Gradle {
        const val ANDROID_JUNIT5 = "1.7.0.0"
        const val FIREBASE_CRASHLYTICS = "2.5.0"
        const val FIREBASE_PERFORMANCE_PLUGIN = "1.3.4"
        const val GRADLE_ANDROID = "4.1.2"
        const val KOTLIN = "1.4.30"
        const val MAVEN_PLUGIN = "2.1"
        const val GOOGLE_SERVICES = "4.3.5"
        const val REMAL_PLUGIN = "1.2.2"
    }

    object Kotlin {
        const val JDK = Gradle.KOTLIN
    }

    object Google {
        object Androidx {
            const val APP_COMPAT = "1.2.0"
            const val CONSTRAINT_LAYOUT = "2.0.4"
            const val CORE_KTX = "1.3.2"
            const val CORE_TESTING = "2.1.0"
            const val ESPRESSO = "3.3.0"
            const val JUNIT_EXT = "1.1.2"
            const val LIFECYCLE_EXTENSIONS = "2.2.0"
            const val LIFECYCLE_LIVEDATA = "2.3.0"
            const val NAVIGATION = "2.3.3"
            const val PALETTE = "1.0.0"
            const val RECYCLERVIEW = "1.2.0-beta01"
            const val TEST_FRAGMENT = "1.3.0"
            const val TEST_CORE = "1.3.0"
            const val TEST_RULES = "1.3.0"
            const val TEST_RUNNER = "1.2.0"
            const val ROOM = "2.2.6"
            const val WORK_MANAGER = "2.5.0"
        }

        object Firebase {
            const val ANALYTICS = "18.0.2"
            const val CRASHLYTICS = "17.3.1"
            const val PERFORMANCE = "19.1.1"
        }

        object Material {
            const val DESIGN = "1.3.0"
        }

        object Test {
            const val TRUTH = "1.1.2"
        }
    }

    object Square {
        const val MOSHI = "1.11.0"
        const val OK_HTTP = "4.9.1"
        const val RETROFIT = "2.9.0"
        const val RETROFIT_CONVERTER_MOSHI = "2.9.0"
        const val RETROFIT_COROUTINES_ADAPTER = "0.9.2"
    }

    object Coroutines {
        const val CORE = "1.4.2"
        const val ANDROID = "1.4.2"
    }

    object Koin {
        const val KOIN = "2.2.2"
    }

    object Test {
        const val APACHE_COMMONS = "20030203.000550"
        const val BARISTA = "3.7.0"
        const val COROUTINES = "1.4.2"
        const val FLOW_TEST_OBSERVER = "1.5.1"
        const val JUNIT = "4.13.1"
        const val JUNIT_JUPITER = "5.7.1"
        const val KAKAO = "2.4.0"
        const val KOIN = Koin.KOIN
        const val KOTEST = "4.4.1"
        const val MOCKITO_KOTLIN = "2.2.0"
        const val MOCKK = "1.10.6"
        const val MOCK_WEB_SERVER = "4.9.1"
        const val ROBOLECTRIC = "4.5.1"
        const val TURBINE = "0.4.0"
    }

    object Detekt {
        const val DETEKT = "1.15.0"
        const val DETEKT_FORMATTING = DETEKT
    }

    object Arrow {
        const val ARROW = "0.10.5"
    }

    object Glide {
        const val GLIDE = "4.8.0"
    }

    object Other {
        const val AIRBNB_LOTTIE = "3.6.0"
        const val CHART = "v3.1.0"
        const val EMOJI = "5.1.1"
    }
}