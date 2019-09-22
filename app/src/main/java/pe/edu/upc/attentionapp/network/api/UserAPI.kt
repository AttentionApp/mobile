package pe.edu.upc.attentionapp.network.api

import pe.edu.upc.attentionapp.models.User
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import pe.edu.upc.attentionapp.network.responses.common.PostResponse
import pe.edu.upc.attentionapp.network.responses.common.StatusResponse
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {
    @GET("users")
    fun findAll(@Header("authorization") authHeader: String): Call<CollectionResponse<User>>

    @GET("users/{id}")
    fun findById(@Path("id") id: Int): Call<DataResponse<User>>

    @POST("users")
    fun save(@Body user: User): Call<PostResponse>

    @PUT("users/{id}")
    fun update(@Path("id") id: Int, @Body nurse: User): Call<StatusResponse>

    @DELETE("users/{id}")
    fun delete(@Path("id") id: Int): Call<StatusResponse>

}