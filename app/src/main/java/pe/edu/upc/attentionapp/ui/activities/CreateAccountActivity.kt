package pe.edu.upc.attentionapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.upc.attentionapp.R

class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        supportActionBar!!.setTitle("Crear Cuenta")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }
}
