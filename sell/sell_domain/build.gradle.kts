apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Coroutines.coroutines)
    "implementation"("androidx.paging:paging-common-ktx:3.1.1")
}