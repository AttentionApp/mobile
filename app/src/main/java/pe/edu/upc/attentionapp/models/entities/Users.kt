package pe.edu.upc.attentionapp.models.entities

import com.google.gson.annotations.SerializedName
import pe.edu.upc.attentionapp.models.entities.Customer

class Users {

    @SerializedName("rows")
    var rows = ArrayList<Customer>()
}