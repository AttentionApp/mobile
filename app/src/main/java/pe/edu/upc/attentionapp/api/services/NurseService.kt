package pe.edu.upc.attentionapp.api.services

import pe.edu.upc.attentionapp.api.dto.response.common.CollectionResponse
import pe.edu.upc.attentionapp.api.dto.response.common.DataResponse
import pe.edu.upc.attentionapp.api.dto.response.common.PostResponse
import pe.edu.upc.attentionapp.api.dto.response.common.StatusResponse
import pe.edu.upc.attentionapp.models.entities.Nurse
import retrofit2.Call
import retrofit2.http.*

interface NurseService {
    @GET("nurses")
    fun findAll(): Call<CollectionResponse<Nurse>>

    @GET("nurses/{id}")
    fun findById(@Path("id") id: Int): Call<DataResponse<Nurse>>

    @POST("nurses")
    fun save(@Body nurse: Nurse): Call<PostResponse>

    @PUT("nurses/{id}")
    fun update(@Path("id") id: Int, @Body nurse: Nurse): Call<StatusResponse>

    @DELETE("nurses/{id}")
    fun delete(@Path("id") id: Int): Call<StatusResponse>
}