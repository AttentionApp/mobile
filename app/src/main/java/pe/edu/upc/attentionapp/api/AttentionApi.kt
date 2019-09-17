package pe.edu.upc.attentionapp.api

import pe.edu.upc.attentionapp.api.request.LoginRequest
import pe.edu.upc.attentionapp.api.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AttentionApi {

    @POST("/login")
    fun login(@Body body:LoginRequest): Call<LoginResponse>




}