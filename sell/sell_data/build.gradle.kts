apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"("androidx.paging:paging-runtime-ktx:3.1.1")

}