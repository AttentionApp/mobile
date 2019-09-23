package pe.edu.upc.attentionapp.network.api

import pe.edu.upc.attentionapp.models.User
import pe.edu.upc.attentionapp.network.responses.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPI {

    @POST("auth/signup")
    fun register(@Body user:User): Call<AuthResponse>

    @POST("auth/login")
    fun login(@Body user:User): Call<AuthResponse>

}