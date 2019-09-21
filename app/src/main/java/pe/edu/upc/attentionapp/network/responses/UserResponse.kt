package pe.edu.upc.attentionapp.network.responses

import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("success")
    var success: Boolean?=false

    @SerializedName("message")
    var message: String = ""

    @SerializedName("token")
    var token: String = ""
}
