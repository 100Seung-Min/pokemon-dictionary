import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}