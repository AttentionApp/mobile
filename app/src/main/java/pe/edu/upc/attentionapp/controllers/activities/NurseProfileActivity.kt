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

        supportActionBar!!.setTitle("Perfil")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val nurse = getIntent().getExtras()!!.getSerializable("nurseObject") as Nurse?

        Picasso.get()
            .load(nurse!!.thumbnailImage)
            .placeholder(R.drawable.ic_user_placeholder_48dp)
            .into(ivNPImage)

        tvNPName.text=nurse!!.firstName
        tvNPLastName.text=nurse!!.lastName



    }
}
