package pe.edu.upc.attentionapp.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*
import pe.edu.upc.attentionapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvCreateAccount.setOnClickListener{
            val intent = Intent(this, CreateAccountActivity::class.java)
            // To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
            // start your next activity
            startActivity(intent)
        }

    }
}
