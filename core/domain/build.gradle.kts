plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.domain"
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.paging)
}