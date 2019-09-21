package pe.edu.upc.attentionapp.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.models.User
import pe.edu.upc.attentionapp.network.api.AuthenticationAPI
import pe.edu.upc.attentionapp.network.responses.UserResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var authenticationAPI:AuthenticationAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        supportActionBar!!.setTitle("Crear Cuenta")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val retrofit= Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        authenticationAPI=retrofit!!.create<AuthenticationAPI>(AuthenticationAPI::class.java)


        btMACreateAccount.setOnClickListener{
            registerPost(etCAFirstName.text.toString(),etCALastName.text.toString(),etCAEmail.text.toString(),etCAPassword.text.toString())
        }


    }

    fun validate(vararg fields:String): Boolean {
        for (item in fields){
            if(item.equals("")){
                return false
            }
        }
        return true
    }

    fun registerPost(firstName:String,lastName:String,email:String , password:String){


        if (validate(firstName,lastName,email,password)){
            Toast.makeText(this@CreateAccountActivity,"Ingresa campos requeridos", Toast.LENGTH_SHORT).show()
        }

        val registerCall=authenticationAPI.register(User(firstName,lastName,email,password))

        registerCall.enqueue(object:Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@CreateAccountActivity,"Error de Ingresos", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.body()!!.success != null){
                    val intent = Intent(this@CreateAccountActivity, HomeActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this@CreateAccountActivity,"Ocurrio un error",Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}
