import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.remote"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    testImplementation(libs.okhttp.sse)
    implementation(libs.paging)
}