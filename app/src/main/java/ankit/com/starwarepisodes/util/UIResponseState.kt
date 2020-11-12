package ankit.com.starwarepisodes.util

import ankit.com.starwarepisodes.util.UIResponseState.Success

/**
 * Created by AnkitSingh on 10/29/20.
 */

sealed class UIResponseState<out T> {
    data class Success<out T>(val data: T) : UIResponseState<T>()
    data class Error(val exception: Exception) : UIResponseState<Nothing>()
    object Loading : UIResponseState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [CurrentState] is of type [Success] & holds non-null [Success.data].
 */
val UIResponseState<*>.succeeded
    get() = this is Success && data != null

fun <T> UIResponseState<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

val <T> UIResponseState<T>.data: T?
    get() = (this as? Success)?.data