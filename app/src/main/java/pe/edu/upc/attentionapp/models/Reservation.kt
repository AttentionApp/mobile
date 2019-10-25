package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Reservation (
    @SerializedName("idcustomer")
    var idCustomer:Int?,

    @SerializedName("idnurse")
    var idNurse:Int?,

    @SerializedName("idcard")
    var idCard :Int?,

    @SerializedName("start_date")
    var startDate: Date?,

    @SerializedName("end_date")
    var endDate: Date?,

    @SerializedName("amount")
    var amount:Int?
)