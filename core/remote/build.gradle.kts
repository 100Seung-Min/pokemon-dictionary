plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.remote"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    testImplementation(libs.okhttp.sse)
    implementation(libs.paging)
}