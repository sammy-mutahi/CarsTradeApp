apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"("androidx.navigation:navigation-fragment-ktx:2.4.0")
    "implementation"("androidx.navigation:navigation-ui-ktx:2.4.0")
    "implementation"("androidx.paging:paging-common-ktx:3.1.1")
    "implementation"("com.facebook.shimmer:shimmer:0.5.0")
}