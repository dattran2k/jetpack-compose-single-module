package com.dat.base_compose.data.remote.service

import com.dat.base_compose.data.model.request.DemoRequest
import com.dat.base_compose.data.model.response.DemoResponse
import com.dat.base_compose.data.remote.Endpoint
import retrofit2.Response
import retrofit2.http.*

interface DemoService {
    // login
    @POST(Endpoint.DEMO)
    suspend fun requestDemo(
        @Body request: DemoRequest,
    ): Response<DemoResponse>

}