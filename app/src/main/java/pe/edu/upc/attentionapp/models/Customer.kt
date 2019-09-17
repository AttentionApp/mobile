package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName

class Customer{

    @SerializedName("first_name")
    var first_name:String=""

    @SerializedName("last_name")
    var last_name:String=""

    @SerializedName("short_name")
    var short_name:String=""

    @SerializedName("gender")
    var gender:String=""

}
