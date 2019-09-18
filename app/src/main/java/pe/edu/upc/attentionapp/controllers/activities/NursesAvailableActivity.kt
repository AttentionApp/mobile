package pe.edu.upc.attentionapp.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_nurses_available.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.adapters.NursesAdapter
import pe.edu.upc.attentionapp.models.Nurse
import pe.edu.upc.attentionapp.network.api.NurseAPI
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NursesAvailableActivity : AppCompatActivity() {

    lateinit var adapter : NursesAdapter
    var nurses = ArrayList<Nurse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurses_available)

        getNursesAvailable()

        nursesRecyclerView.layoutManager = GridLayoutManager(this,2)
    }

    private fun getNursesAvailable() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val nurseAPI = retrofit.create(NurseAPI::class.java)
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFsdmFyYWRvQGdtYWlsLmNvbSIsImlhdCI6MTU2ODgzNjI1MCwiZXhwIjoxNTY4ODM2MjUwfQ.M7Sivfon1fzW2QEUDpPyqfnhaXByvEy9AWX-p1-YvUg"
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
                    nurses = response.body()!!.rows
                    adapter = NursesAdapter(nurses)
                    nursesRecyclerView.adapter = adapter
                }
            }

        })
    }


}
