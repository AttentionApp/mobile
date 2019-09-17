package pe.edu.upc.attentionapp.api.dto.response.common

import com.google.gson.annotations.SerializedName

class CollectionResponse<T>{
    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("numRows")
    var numRows: Int = 0

    @SerializedName("rows")
    lateinit var rows: ArrayList<T>
}