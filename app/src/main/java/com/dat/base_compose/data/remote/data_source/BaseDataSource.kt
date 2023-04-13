package com.dat.base_compose.data.remote.data_source

import android.content.Context
import com.dat.base_compose.data.remote.Resource
import com.dat.base_compose.utils.InternetUtil
import com.dat.base_compose.utils.PreferenceHelper
import retrofit2.Response

abstract class BaseDataSource(val context: Context) {


    val pref = PreferenceHelper.getInstance()

    /**
     * map data to Resource
     */
    suspend fun <T : Any> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        try {
            if (!InternetUtil.internetState.value) {
                return Resource.error("No Internet")
            }
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
            val errorBody = response.errorBody()?.string() ?: "Error"
            if (response.code() == 400) {
                try {
                    return Resource.error(errorBody)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return Resource.error(
                "${response.code()}|${response.message()}",
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error(e.message ?: "Error")
        }
    }
}