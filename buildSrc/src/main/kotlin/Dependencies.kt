object Dependencies {

    object AndroidX {
        object Core {
            private const val VERSION = "1.2.0"
            const val KTX = "androidx.core:core-ktx:$VERSION"
        }

        object ConstraintLayout {
            private const val VERSION = "2.0.0-beta6"
            const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$VERSION"
        }

        object AppCompat {
            private const val VERSION = "1.0.2"
            const val APPCOMPAT = "androidx.appcompat:appcompat:$VERSION"
        }

        object Compose {
            const val VERSION = "1.0.0-alpha06"

            const val UI = "androidx.compose.ui:ui:$VERSION"
            const val MATERIAL = "androidx.compose.material:material:$VERSION"
            const val TOOLING = "androidx.ui:ui-tooling:$VERSION"
        }
    }

    object Google {

        object Material {
            private const val VERSION = "1.2.0"
            const val MATERIAL = "com.google.android.material:material:$VERSION"
        }

    }

    object Kotlin {

        const val VERSION = "1.4.10"

        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:$VERSION"

    }
}
