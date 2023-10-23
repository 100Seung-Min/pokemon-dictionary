plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.desgin_system"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.coil)
}