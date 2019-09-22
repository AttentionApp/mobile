package pe.edu.upc.attentionapp.network.responses

import com.google.gson.annotations.SerializedName
import pe.edu.upc.attentionapp.models.User

class AuthResponse {

    @SerializedName("success")
    var success: Boolean?=false

    @SerializedName("message")
    var message: String = ""

    @SerializedName("token")
    var token: String = ""

    @SerializedName("userData")
    var dataUser: User? = null

    @SerializedName("thumbnail_image")
    var thumbnailImage: String = ""

}
