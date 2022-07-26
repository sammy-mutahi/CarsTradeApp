apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)

}