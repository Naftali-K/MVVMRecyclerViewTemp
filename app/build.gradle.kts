plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mvvmrecyclerviewtemp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmrecyclerviewtemp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.github.bumptech.glide:glide:4.16.0")

//    implementation("android.arch.lifecycle:extensions:1.1.1")
//    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    implementation("androidx.lifecycle:lifecycle-viewmodel:2.4.0")
//    implementation("android.arch.lifecycle:viewmodel:1.1.0")
}