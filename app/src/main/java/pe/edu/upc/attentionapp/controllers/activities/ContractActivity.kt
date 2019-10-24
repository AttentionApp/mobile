package pe.edu.upc.attentionapp.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.upc.attentionapp.R

class ContractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}
