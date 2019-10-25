package pe.edu.upc.attentionapp.network.api

import pe.edu.upc.attentionapp.models.Card
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import pe.edu.upc.attentionapp.network.responses.common.PostResponse
import pe.edu.upc.attentionapp.network.responses.common.StatusResponse
import pe.edu.upc.attentionapp.models.Nurse
import retrofit2.Call
import retrofit2.http.*

interface CardAPI {
    @GET("cards")
    fun findAll(@Header("authorization") authHeader: String): Call<CollectionResponse<Card>>

    @GET("cards/{id}")
    fun findById(@Path("id") id: Int): Call<DataResponse<Card>>

    @POST("cards")
    fun save(@Body card: Card): Call<PostResponse>

    @PUT("cards/{id}")
    fun update(@Path("id") id: Int, @Body card: Card): Call<StatusResponse>

    @DELETE("cards/{id}")
    fun delete(@Path("id") id: Int): Call<StatusResponse>

    

}