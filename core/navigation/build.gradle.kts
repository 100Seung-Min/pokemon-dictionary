plugins {
    id("pokemon-core")
}

android {
    namespace = "com.pokemon.core.navigation"
}

dependencies {
    implementation(project(":core:design-system"))
}