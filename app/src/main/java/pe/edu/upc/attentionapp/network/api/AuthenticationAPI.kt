package pe.edu.upc.attentionapp.network.api

import pe.edu.upc.attentionapp.models.User
import pe.edu.upc.attentionapp.network.responses.AuthResponse
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationAPI {

    @POST("auth/signup")
    fun register(@Body user:User): Call<AuthResponse>

    @POST("auth/login")
    fun login(@Body user:User): Call<AuthResponse>

    @GET("auth/self")
    fun data(@Header("authorization")authHeader: String): Call<DataResponse<User>>

}