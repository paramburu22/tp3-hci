package com.example.contrall.data.network

import com.example.contrall.data.network.models.DeviceResult
import com.example.contrall.data.network.models.DevicesList
import com.example.contrall.data.network.models.RoutinesList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/api/devices")
    suspend fun getDevices() : Response<DevicesList>

    @GET("/api/devices/{id}")
    suspend fun getDevice(@Path("id")id: String): Response<DeviceResult>


//    @GET("/api/devices/{id}")
//    suspend fun getDevice(@Path("id")id: String): Response<DeviceResult>

//    @POST("/api/devices")
//    suspend fun addNewDevice(@Body device: DeviceToAdd) : Response<Device>
//
   @GET("/api/routines")
    suspend fun getRoutines() : Response<RoutinesList>

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun executePS(@Path("deviceId")deviceId: String, @Path("actionName")actionName: String, @Body params: List<String> )

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun executePI(@Path("deviceId")deviceId: String, @Path("actionName")actionName: String, @Body params: List<Int> )

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun execute(@Path("deviceId")deviceId: String, @Path("actionName")actionName: String )

    @PUT("/api/routines/{routineId}/execute")
    suspend fun executeRoutine(@Path("routineId")routineId: String)
}