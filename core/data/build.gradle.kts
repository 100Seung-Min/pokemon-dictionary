plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.data"
}

dependencies {
    implementation(project(":core:remote"))
    implementation(project(":core:local"))
    implementation(project(":core:domain"))
    implementation(libs.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}