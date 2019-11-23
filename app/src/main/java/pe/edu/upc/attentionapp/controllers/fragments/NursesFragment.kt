package pe.edu.upc.attentionapp.controllers.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_nurses.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.adapters.NursesAdapter
import pe.edu.upc.attentionapp.controllers.activities.FindNurseActivity
import pe.edu.upc.attentionapp.models.Nurse
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

class NursesFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var adapter : NursesAdapter
    var nurses: List<Nurse> = ArrayList<Nurse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nurses,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = view.context.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE)
        getNursesAvailable()
        rvFNNurses.layoutManager = LinearLayoutManager(view.context)
        fbFNContract.setOnClickListener {
            val intent = Intent(context, FindNurseActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getNursesAvailable() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val nurseAPI = retrofit.create(NurseAPI::class.java)
        val token = sharedPreferences.getString(SHARED_PREFERENCES_FIELD_TOKEN,"")
        val nurseCall = nurseAPI.findAll("Bearer $token")

        nurseCall.enqueue(object: Callback<CollectionResponse<Nurse>> {
            override fun onFailure(call: Call<CollectionResponse<Nurse>>, t: Throwable) {
                Log.d("Exception: ", t.toString())
            }

            override fun onResponse(
                call: Call<CollectionResponse<Nurse>>,
                response: Response<CollectionResponse<Nurse>>
            ) {
                if(response.isSuccessful){
                    val context: Context = context!!
                    nurses = response.body()!!.rows
                    adapter = NursesAdapter(nurses,context)
                    rvFNNurses.adapter = adapter
                }
            }

        })
    }
}