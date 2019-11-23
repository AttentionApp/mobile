package pe.edu.upc.attentionapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*


class Reservation :Serializable {
    @SerializedName("idcustomer")
    var idCustomer=0

    @SerializedName("idnurse")
    var idNurse=0

    @SerializedName("idcard")
    var idCard=0

    @SerializedName("start_date")
    var startDate: Date?=null

    @SerializedName("end_date")
    var endDate: Date?=null

    @SerializedName("amount")
    var amount=0

    @SerializedName("latitude")
    var latitude: Double? = null

    @SerializedName("longitude")
    var longitude: Double? = null
}