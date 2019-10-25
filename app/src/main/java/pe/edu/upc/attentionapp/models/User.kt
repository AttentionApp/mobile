package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("idcustomer")
    var idCustomer:Int?,

    @SerializedName("first_name")
    var firstName:String?,

    @SerializedName("last_name")
    var lastName:String?,

    @SerializedName("email")
    var email :String?,

    @SerializedName("password")
    var password:String?,

    @SerializedName("short_name")
    var shortName: String?,

    @SerializedName("thumbnail_image")
    var thumbnailImage: String?


)