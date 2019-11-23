package pe.edu.upc.attentionapp.controllers.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_contract.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.adapters.NursesAdapter
import pe.edu.upc.attentionapp.adapters.NursesFilterAdapter
import pe.edu.upc.attentionapp.models.Nurse
import pe.edu.upc.attentionapp.models.Reservation
import pe.edu.upc.attentionapp.network.api.NurseAPI
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import pe.edu.upc.attentionapp.util.AttentionAppConfig.Companion.SHARED_PREFERENCES_FIELD_TOKEN
import pe.edu.upc.attentionapp.util.AttentionAppConfig.Companion.SHARED_PREFERENCES_NAME
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContractActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var adapter : NursesFilterAdapter
    var nurses: List<Nurse> = ArrayList<Nurse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)



        getNursesAvailable()
        rvCONursesAvailable.layoutManager= LinearLayoutManager(this)

    }


    private fun getNursesAvailable() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val nurseAPI = retrofit.create(NurseAPI::class.java)
        val token = sharedPreferences.getString(SHARED_PREFERENCES_FIELD_TOKEN,"")
        val nurseCall = nurseAPI.filter("Bearer $token")

        nurseCall.enqueue(object: Callback<CollectionResponse<Nurse>> {
            override fun onFailure(call: Call<CollectionResponse<Nurse>>, t: Throwable) {
                Log.d("Exception: ", t.toString())
            }

            override fun onResponse(
                call: Call<CollectionResponse<Nurse>>,
                response: Response<CollectionResponse<Nurse>>
            ) {
                if(response.isSuccessful){
                    val contract = intent.extras!!.getSerializable("contract") as Reservation?
                    nurses = response.body()!!.rows
                    adapter = NursesFilterAdapter(nurses,this@ContractActivity, contract!!)
                    rvCONursesAvailable.adapter = adapter
                }
            }

        })
    }

}
