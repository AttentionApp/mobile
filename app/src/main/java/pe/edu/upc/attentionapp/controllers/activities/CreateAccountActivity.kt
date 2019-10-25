package pe.edu.upc.attentionapp.controllers.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_create_account.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.models.User
import pe.edu.upc.attentionapp.network.api.AuthenticationAPI
import pe.edu.upc.attentionapp.network.responses.AuthResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var authenticationAPI:AuthenticationAPI
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        supportActionBar!!.setTitle("Crear Cuenta")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val retrofit= Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        sharedPreferences = getSharedPreferences(AttentionAppConfig.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        authenticationAPI=retrofit!!.create<AuthenticationAPI>(AuthenticationAPI::class.java)


        btCARegister.setOnClickListener{
            registerPost(etCAFirstName.text.toString(),etCALastName.text.toString(),etCAEmail.text.toString(),etCAPassword.text.toString())
        }


    }

    fun registerPost(firstName:String,lastName:String,email:String , password:String){

        var isFirsName=firstName.validator()
            .nonEmpty()
            .addErrorCallback {
                etCAFirstName.error = "Ingresa tu Nombre"
            }
            .check()

        var isLastName=lastName.validator()
            .nonEmpty()
            .addErrorCallback {
                etCALastName.error = "Ingresa tu Apellido"
            }
            .check()

        var isEmail=email.validEmail(){
            etCAEmail.error = "Ingresa el correo electronico"
        }

        var isPassword=password.validator()
            .nonEmpty()
            .addErrorCallback {
                etCAPassword.error = "Ingresa la contraseña"
            }
            .check()


        if(isFirsName&&isLastName&&isEmail&&isPassword){
            val registerCall=authenticationAPI.register(User(null,firstName,lastName,email,password,null,null))

            registerCall.enqueue(object:Callback<AuthResponse>{
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@CreateAccountActivity,"Error", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if(response.isSuccessful){
                        if(response.body()!!.success!=null){
                            val intent = Intent(this@CreateAccountActivity, HomeActivity::class.java)

                            val token = response.body()!!.token
                            val editor = sharedPreferences.edit()

                            editor.putString(AttentionAppConfig.SHARED_PREFERENCES_FIELD_TOKEN,token)
                            editor.commit()

                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this@CreateAccountActivity,"Ocurrio un error",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this@CreateAccountActivity,"Usuario ya existe",Toast.LENGTH_SHORT).show()
                    }

                }
            })
        }


    }

}
