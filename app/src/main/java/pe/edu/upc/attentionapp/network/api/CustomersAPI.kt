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

interface CustomersAPI {

    @GET("customers/{id}/cards")
    fun getCards(@Header("authorization") authHeader: String, @Path("id") id: Int):Call<CollectionResponse<Card>>

    @GET("customers/{id}/reservations")
    fun getReservations(@Header("authorization") authHeader: String, @Path("id") id: Int):Call<CollectionResponse<Reservation>>

}