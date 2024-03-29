package pe.edu.upc.attentionapp.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_nurses_profile.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.models.Nurse
import java.io.Serializable

class NurseProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurses_profile)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val nurse = intent.extras!!.getSerializable("nurseObject") as Nurse?
        supportActionBar!!.title = "Perfil de ${nurse!!.shortName}"

        Picasso.get()
            .load(nurse!!.thumbnailImage)
            .placeholder(R.drawable.ic_user_placeholder_48dp)
            .into(ivNPImage)

        tvNPShortName.text=nurse!!.shortName
        tvNPDNI.text=nurse!!.codeIdentification

        if(nurse.idNurseType==1){
            tvNPNurseType.text="Licenciada(o)"
        }else {
            tvNPNurseType.text = "Tecnica(o)"
        }
        tvNPGender.text=nurse!!.gender
        tvNPDescription.text=nurse!!.description
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
