package pe.edu.upc.attentionapp.controllers.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.controllers.activities.MainActivity
import pe.edu.upc.attentionapp.models.User
import pe.edu.upc.attentionapp.network.api.AuthenticationAPI
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment: Fragment() {

    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = view.context.getSharedPreferences(AttentionAppConfig.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        getData()

        btFPSignOut.setOnClickListener {
            val editor = sharedPreferences.edit()

            editor.putString(AttentionAppConfig.SHARED_PREFERENCES_FIELD_TOKEN,"")
            editor.commit()
            val intent = Intent(view.context,MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }

    }

    private fun getData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authenticationAPI = retrofit.create(AuthenticationAPI::class.java)
        val token = sharedPreferences.getString(AttentionAppConfig.SHARED_PREFERENCES_FIELD_TOKEN,"")
        val userCall = authenticationAPI.data("Bearer $token")


        userCall.enqueue(object: Callback<DataResponse<User>>{
            override fun onFailure(call: Call<DataResponse<User>>, t: Throwable) {
                Log.d("Exception: ", t.toString())
            }

            override fun onResponse(
                call: Call<DataResponse<User>>,
                response: Response<DataResponse<User>>
            ) {
                if(response.isSuccessful){

                    var user = response.body()!!.data
                    var email = user!!.email.toString()
                    var firtsName = user!!.firstName.toString()
                    var lastName = user!!.lastName.toString()
                    var shortName = user!!.shortName.toString()
                    var image = user!!.thumbnailImage.toString()

                    tvFPEmail.setText(email)
                    tvFPShortName.setText(shortName)


                }

            }
        })
    }

}