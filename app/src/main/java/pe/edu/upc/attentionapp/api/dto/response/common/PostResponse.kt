package pe.edu.upc.attentionapp.api.dto.response.common

import com.google.gson.annotations.SerializedName

class PostResponse {
    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("insertId")
    var insertId: Int = 0
}