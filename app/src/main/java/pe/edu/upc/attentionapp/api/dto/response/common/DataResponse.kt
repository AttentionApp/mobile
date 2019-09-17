package pe.edu.upc.attentionapp.api.dto.response.common

import com.google.gson.annotations.SerializedName

class DataResponse <T> (){

    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("numRows")
    var numRows: Int = 0

    @SerializedName("data")
    var data: T? = null
}