import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.data"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
    }
}

dependencies {
    implementation(project(":core:remote"))
    implementation(project(":core:domain"))
    implementation(libs.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}