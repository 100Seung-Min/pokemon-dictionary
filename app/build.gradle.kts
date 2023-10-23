plugins {
    id("pokemon-app")
}

android {
    namespace = "com.pokemon.app"

    defaultConfig {
        applicationId = "com.pokemon.app"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }
}
dependencies {
    implementation(project(":feature:main"))
    implementation(project(":feature:pokemon"))
}

