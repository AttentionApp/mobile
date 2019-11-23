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
import kotlinx.android.synthetic.main.fragment_nurses.*
import kotlinx.android.synthetic.main.fragment_reservations.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.adapters.NursesAdapter
import pe.edu.upc.attentionapp.controllers.activities.FindNurseActivity
import pe.edu.upc.attentionapp.models.Nurse
import pe.edu.upc.attentionapp.models.Reservation
import pe.edu.upc.attentionapp.network.api.CustomersAPI
import pe.edu.upc.attentionapp.network.api.NurseAPI
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment: Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    var nurse: List<Nurse> = ArrayList<Nurse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = view.context.getSharedPreferences(
            AttentionAppConfig.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE)
    }


    private fun getNursesAvailable() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val customersAPI = retrofit.create(CustomersAPI::class.java)
        val idCustomer= sharedPreferences.getInt(AttentionAppConfig.SHARED_PREFERENCES_FIELD_IDCUSTOMER,0)
        val token = sharedPreferences.getString(AttentionAppConfig.SHARED_PREFERENCES_FIELD_TOKEN,"")
        val nurseCall = customersAPI.getReservations("Bearer $token",idCustomer)
    }
}