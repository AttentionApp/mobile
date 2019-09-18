package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName
import pe.edu.upc.attentionapp.models.Customer

class Users {

    @SerializedName("rows")
    var rows = ArrayList<Customer>()
}