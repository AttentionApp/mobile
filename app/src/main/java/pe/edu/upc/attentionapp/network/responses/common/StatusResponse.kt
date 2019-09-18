package pe.edu.upc.attentionapp.network.responses.common

import com.google.gson.annotations.SerializedName

class StatusResponse {
    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("message")
    var message: String = ""
}