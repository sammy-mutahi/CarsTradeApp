apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"("androidx.navigation:navigation-fragment-ktx:2.4.0")
    "implementation"("androidx.navigation:navigation-ui-ktx:2.4.0")
}