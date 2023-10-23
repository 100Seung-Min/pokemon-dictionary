plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.local"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.androidx.preference)
    implementation(libs.room)
    ksp(libs.room.compiler)
}