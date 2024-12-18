plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.sozge.taratornew"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sozge.taratornew"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.4")

    // Image
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Navigation
    val nav_version = "2.6.0-rc01"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //BottomSheet
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))

    //Filters
    implementation("androidx.compose.material3:material3:1.2.0")

    implementation ("androidx.compose.ui:ui:1.7.5") // veya daha yeni sürüm
    implementation ("androidx.compose.foundation:foundation:1.7.5") // veya daha yeni sürüm

    //Lottie Animation
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    //icons
    implementation ("androidx.compose.material:material-icons-extended:<1.7.5>")

}