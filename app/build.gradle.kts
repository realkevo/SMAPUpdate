plugins {
    id("com.android.application")
    id("com.google.gms.google-services")

}

android {
    namespace = "ke.co.smap"
    compileSdk = 34

    defaultConfig {
        applicationId = "ke.co.smap"
        minSdk = 24
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.firebase:firebase-inappmessaging:20.4.0")
    implementation("com.squareup.picasso:picasso:2.5.2")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.jpardogo.googleprogressbar:library:1.2.0")
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.1")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("com.google.android.material:material:1.3.0-alpha02")
    implementation ("com.google.firebase:firebase-database:19.3.1")

    //8!tch *ss recyclerview dependencies!!..
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("com.firebaseui:firebase-ui-database:8.0.1")
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation ("com.google.firebase:firebase-storage:19.1.0")


    implementation ("com.google.gms:google-services:3.0.0")


    implementation ("com.squareup.picasso:picasso:2.8")

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    //implementation("com.google.firebase:firebase-crashlytics")
    //implementation("com.google.firebase:firebase-analytics")


}
