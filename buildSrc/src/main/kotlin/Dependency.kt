object Dependency {

    object GradlePlugin {
        const val GRADLE_ANDROID = "com.android.tools.build:gradle:${Version.GRADLE_ANDROID}"
        const val GRADLE_KOTLIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.GRADLE_KOTLIN}"
        const val GRADLE_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
    }

    object Kotlin {
        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.KOTLINX_COROUTINES}"
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.KOTLINX_COROUTINES}"
    }

    object AndroidX {
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"

        const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"

        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"

        const val LIFECYCLE_VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE_KTX}"
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE_KTX}"

        const val PAGING = "androidx.paging:paging-runtime-ktx:${Version.PAGING}"
        const val REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.REFRESH}"
    }

    object Hilt {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
    }

    object Room {
        const val ROOM = "androidx.room:room-ktx:${Version.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
        const val FLEX_BOX = "com.google.android.flexbox:flexbox:${Version.FLEX_BOX}"
    }

    object Retrofit {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Version.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Version.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"
    }

    object UnitTest {
        const val JUNIT = "junit:junit:${Version.JUNIT}"
        const val MOCKITO = "org.mockito:mockito-core:${Version.MOCKITO}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Version.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"
    }

    object BottomNav {
        const val NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Version.NAV}"
        const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${Version.NAV}"
    }

    object Coil {
        const val COIL = "io.coil-kt:coil:${Version.COIL}"
    }

    object BootPay {
        const val BOOT_PAY = "io.github.bootpay:android:${Version.BootPay}"
    }

    object RxJava {
        const val RX_JAVA = "io.reactivex.rxjava3:rxjava:${Version.RX_JAVA}"
        const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:${Version.RX_ANDROID}"
        const val RX_BINDING = "com.jakewharton.rxbinding3:rxbinding:${Version.RX_BINDING}"
    }

    object Lottie {
        const val LOTTIE = "com.airbnb.android:lottie:${Version.LOTTIE}"
    }
}