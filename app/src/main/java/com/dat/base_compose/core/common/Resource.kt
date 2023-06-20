package com.dat.base_compose.core.common

import com.dat.base_compose.core.util.InternetUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response


/**
 * This is used for getting states of network call
 */
sealed interface Resource<in T : Any> {
    data class Success<T : Any>(val data: T) : Resource<T>
    object Loading : Resource<Any>
    data class Error<T : Any>(val message: String, val code: Int = -1, val data: T? = null) : Resource<T>
}

suspend fun <T : Any> safeApiCall(
    dispatcher: CoroutineDispatcher,
    request: (suspend () -> Response<T>),
): Resource<T> {
    return withContext(dispatcher) {
        try {
            if (!InternetUtil.isNetworkAvailable()) {
                return@withContext Resource.Error("No Internet", 0)
            }
            val response = request.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@withContext Resource.Success(body)
                }
            }
            return@withContext Resource.Error(response.errorBody()?.string() ?: response.message(), response.code())
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message ?: "Some error", 0)
        }
    }
}

fun <T : Any> flowSafeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    request: (suspend () -> Response<T>),

): Flow<Resource<T>> {
    return flow {
        emit(Resource.Loading)
        if (!InternetUtil.isNetworkAvailable()) {
            emit(Resource.Error("No Internet", 0))
            return@flow
        }
        val response = request.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Resource.Success(body))
                return@flow
            }
        }
        emit(Resource.Error(response.errorBody()?.string() ?: response.message(), response.code()))
    }.catch {
        emit(Resource.Error(it.message ?: "Lỗi", 0))
    }.flowOn(dispatcher)
}
