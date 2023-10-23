plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.ui"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:domain"))
    implementation(libs.bundles.compose)
    implementation(libs.coil)
}