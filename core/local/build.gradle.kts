plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.local"
}

dependencies {
    implementation(project(":core:data"))
    implementation(libs.androidx.preference)
    implementation(libs.room)
    ksp(libs.room.compiler)
}