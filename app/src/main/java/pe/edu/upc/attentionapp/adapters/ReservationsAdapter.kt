package pe.edu.upc.attentionapp.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_nurses.*
import kotlinx.android.synthetic.main.item_reservation.view.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.controllers.activities.ReservationActivity
import pe.edu.upc.attentionapp.models.Nurse
import pe.edu.upc.attentionapp.models.Reservation
import pe.edu.upc.attentionapp.network.api.NurseAPI
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.network.responses.common.DataResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class ReservationsAdapter(private var reservations: List<Reservation>, var context: Context, var sharedPreferences: SharedPreferences): RecyclerView.Adapter<ReservationsAdapter.ReservationViewHolder>() {

    inner class ReservationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindTo(reservation: Reservation){
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
            itemView.tvAmount.text = reservation.amount.toString()
            itemView.tvStartDate.text = formatter.format(reservation.startDate)
            itemView.tvEndDate.text = formatter.format(reservation.endDate)
            val retrofit = Retrofit.Builder()
                .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val nurseAPI = retrofit.create(NurseAPI::class.java)
            val token = sharedPreferences.getString(AttentionAppConfig.SHARED_PREFERENCES_FIELD_TOKEN,"")
            val nurseCall = nurseAPI.findById("Bearer $token", reservation.idNurse)
            nurseCall.enqueue(object: Callback<DataResponse<Nurse>> {
                override fun onFailure(call: Call<DataResponse<Nurse>>, t: Throwable) {
                    Log.d("Exception: ", t.toString())
                }

                override fun onResponse(
                    call: Call<DataResponse<Nurse>>,
                    response: Response<DataResponse<Nurse>>
                ){
                    if(response.isSuccessful){
                        val nurse = response.body()!!.data!!
                        Picasso
                            .get()
                            .load(nurse.thumbnailImage)
                            .placeholder(R.drawable.ic_user_placeholder_48dp)
                            .into(itemView.ivNurse)
                    }
                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        return ReservationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_reservation,parent,false))
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.bindTo(reservation)
        holder.itemView.cvReservation.setOnClickListener {
            val intent = Intent(context,ReservationActivity::class.java)
            intent.putExtra("reservation",reservation)
            context.startActivity(intent)
        }
    }
}