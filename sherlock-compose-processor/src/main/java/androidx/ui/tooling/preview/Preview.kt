package androidx.ui.tooling.preview

/**
 * Hacky way to avoid adding Android dependencies
 */

@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FUNCTION
)
@Repeatable
annotation class Preview
