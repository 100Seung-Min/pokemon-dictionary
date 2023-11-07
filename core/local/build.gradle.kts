plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.local"
}

dependencies {
    implementation(libs.androidx.preference)
    implementation(libs.room)
    ksp(libs.room.compiler)
}