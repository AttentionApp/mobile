package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Card : Serializable {
    @SerializedName("idcustomer")
    var idCustomer = ""

    @SerializedName("bank_name")
    var bankName = ""

    @SerializedName("account_number")
    var accountNumber = ""
}