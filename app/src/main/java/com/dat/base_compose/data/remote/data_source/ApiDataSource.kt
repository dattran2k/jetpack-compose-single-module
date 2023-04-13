package com.dat.base_compose.data.remote.data_source

import android.content.Context
import com.dat.base_compose.data.model.request.DemoRequest
import com.dat.base_compose.data.remote.service.DemoService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val demoService: DemoService,
    context: Context,
) : BaseDataSource(context) {

    suspend fun demoRequest(demoRequest: DemoRequest) = safeApiCall { demoService.requestDemo(demoRequest) }
}