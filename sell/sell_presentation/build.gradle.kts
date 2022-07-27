apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"(Google.material)
    "implementation"("androidx.navigation:navigation-fragment-ktx:2.4.0")
    "implementation"("androidx.navigation:navigation-ui-ktx:2.4.0")
    "implementation"("androidx.paging:paging-common-ktx:3.1.1")
    "implementation"("com.facebook.shimmer:shimmer:0.5.0")
    "implementation"("androidx.paging:paging-runtime-ktx:3.1.1")
    // For WebP support, including animated WebP
    "implementation"("com.facebook.fresco:animated-webp:2.6.0")
    "implementation"("com.facebook.fresco:webpsupport:2.6.0")
    "implementation"("com.facebook.fresco:fresco:2.6.0")
    "implementation"("io.coil-kt:coil:2.1.0")
    "implementation"("io.coil-kt:coil-svg:2.1.0")
}