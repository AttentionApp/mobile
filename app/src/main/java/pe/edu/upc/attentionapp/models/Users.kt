package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName

class Users {

    @SerializedName("rows")
    var rows = ArrayList<Customer>()
}