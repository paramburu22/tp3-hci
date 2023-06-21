package com.example.contrall.data.network

import com.example.contrall.data.PlaylistResult
import com.example.contrall.data.SongResult
import com.example.contrall.data.network.models.DeviceResult
import com.example.contrall.data.network.models.DevicesList
import com.example.contrall.data.network.models.RoutinesList
import com.example.contrall.data.network.models.State
import com.example.contrall.data.network.models.StateResult
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

    @PUT("/api/devices/{deviceId}/getPlaylist")
    suspend fun getPlaylist(@Path("deviceId")id: String): Response<PlaylistResult>

    @GET("/api/devices/{id}/state")
    suspend fun getState(@Path("id")id: String): Response<StateResult>



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