package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName

class Customer{

    @SerializedName("first_name")
    var firstName:String=""

    @SerializedName("last_name")
    var lastName:String=""

    @SerializedName("short_name")
    var shortName:String=""

    @SerializedName("gender")
    var gender:String=""

}
