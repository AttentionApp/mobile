package pe.edu.upc.attentionapp.api.services

import pe.edu.upc.attentionapp.api.dto.request.LoginRequest
import pe.edu.upc.attentionapp.api.dto.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>




}