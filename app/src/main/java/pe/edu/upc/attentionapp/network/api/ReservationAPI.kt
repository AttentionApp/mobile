package pe.edu.upc.attentionapp.network.api

import pe.edu.upc.attentionapp.models.Card
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import pe.edu.upc.attentionapp.network.responses.common.PostResponse
import pe.edu.upc.attentionapp.network.responses.common.StatusResponse
import pe.edu.upc.attentionapp.models.Nurse
import pe.edu.upc.attentionapp.models.Reservation
import retrofit2.Call
import retrofit2.http.*

interface ReservationAPI {
    @GET("reservations")
    fun findAll(@Header("authorization") authHeader: String): Call<CollectionResponse<Reservation>>

    @GET("reservations/{id}")
    fun findById(@Path("id") id: Int): Call<DataResponse<Reservation>>

    @POST("reservations")
    fun save(@Header("authorization")authHeader: String,@Body reservation: Reservation): Call<PostResponse>

    @PUT("reservations/{id}")
    fun update(@Path("id") id: Int, @Body reservation: Reservation): Call<StatusResponse>

    @DELETE("reservations/{id}")
    fun delete(@Path("id") id: Int): Call<StatusResponse>

}