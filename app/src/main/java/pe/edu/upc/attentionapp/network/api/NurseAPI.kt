package pe.edu.upc.attentionapp.network.api

import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import pe.edu.upc.attentionapp.network.responses.common.PostResponse
import pe.edu.upc.attentionapp.network.responses.common.StatusResponse
import pe.edu.upc.attentionapp.models.Nurse
import retrofit2.Call
import retrofit2.http.*

interface NurseAPI {
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