object AndroidTestDependencies {
    object AndroidX {

        object Test {
            private const val ANDROID_X_TEST_EXT_VERSION = "1.1.1"
            private const val ANDROID_X_TEST_ESPRESSO_VERSION = "3.2.0"
            const val JUNIT_KTX = "androidx.test.ext:junit-ktx:$ANDROID_X_TEST_EXT_VERSION"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:$ANDROID_X_TEST_ESPRESSO_VERSION"
        }
    }
}
