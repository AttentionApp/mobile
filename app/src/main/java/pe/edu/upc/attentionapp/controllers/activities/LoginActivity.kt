package pe.edu.upc.attentionapp.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_login.*
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

class LoginActivity : AppCompatActivity() {


    private lateinit var authenticationAPI:AuthenticationAPI

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

        val retrofit=Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authenticationAPI=retrofit!!.create<AuthenticationAPI>(AuthenticationAPI::class.java)

        btLOLogin.setOnClickListener{
            loginPost(etLOEmail.text.toString(),etLOPassword.text.toString())
        }
    }


    fun loginPost(email:String , password:String) {

        var isEmail=email.validEmail(){
            etLOEmail.error = "Ingresa correo electronico"
        }

        var isPassword=password.validator()
            .nonEmpty()
            .addErrorCallback {
                etLOPassword.error = "Ingresa contraseña"
            }
            .check()

        if(isEmail&&isPassword){
            val loginCall = authenticationAPI.login(User(null,null,email,password,null,null))
            loginCall.enqueue(object: Callback<AuthResponse>{
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {

                    Toast.makeText(this@LoginActivity,t!!.message!!,Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {

                    if(response.isSuccessful){
                        if(response.body()!!.success!=null){

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this@LoginActivity,"Ocurrio un error",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this@LoginActivity,"Usuario no existe",Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }








    }

}
