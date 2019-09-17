package pe.edu.upc.attentionapp.models.entities

import com.google.gson.annotations.SerializedName

class Nurse {
    @SerializedName("first_name")
    var firstName = ""

    @SerializedName("last_name")
    var lastName = ""

    @SerializedName("short_name")
    var shortName = ""

    @SerializedName("code_identification")
    var codeIdentification = ""

    @SerializedName("gender")
    var gender = ""

    @SerializedName("qualification")
    var qualification: Double = 0.0

    @SerializedName("thumbnail_image")
    var thumbnailImage = ""

    @SerializedName("comment")
    var comment = ""

    @SerializedName("description")
    var description = ""

    @SerializedName("idnursetype")
    var idNurseType: Int = 0
}