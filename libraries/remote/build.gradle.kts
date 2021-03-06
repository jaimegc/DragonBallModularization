plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.Android.COMPILE_SDK)
    buildToolsVersion = Versions.Android.BUILD_TOOLS

    defaultConfig {
        minSdkVersion(Versions.Android.DefaultConfig.MIN_ANDROID_SDK)
        targetSdkVersion(Versions.Android.DefaultConfig.TARGET_ANDROID_SDK)

        testInstrumentationRunner = Versions.Android.DefaultConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xinline-classes",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.ObsoleteCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview",
            "-Xopt-in=org.koin.core.component.KoinApiExtension",
            "-Xopt-in=kotlin.time.ExperimentalTime"
        )
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.Libraries.BASE))

    implementation(Dependencies.KOTLIN_JDK)
    implementation(Dependencies.ANDROID_APP_COMPAT)
    implementation(Dependencies.ANDROID_CORE_KTX)
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.SQUARE_MOSHI)
    implementation(Dependencies.SQUARE_MOSHI_CODEGEN)
    implementation(Dependencies.SQUARE_MOSHI_KOTLIN)
    implementation(Dependencies.SQUARE_OK_HTTP)
    implementation(Dependencies.SQUARE_OK_HTTP_LOGGING_INTERCEPTOR)
    implementation(Dependencies.SQUARE_RETROFIT)
    implementation(Dependencies.SQUARE_RETROFIT_CONVERTER_MOSHI)
    implementation(Dependencies.SQUARE_RETROFIT_COROUTINES_ADAPTER)
    implementation(Dependencies.KOIN)

    api(Dependencies.SQUARE_MOSHI)

    testImplementation(Dependencies.TEST_JUNIT)
    androidTestImplementation(Dependencies.ANDROID_ESPRESSO_CORE)
    androidTestImplementation(Dependencies.ANDROID_JUNIT_EXT)
}