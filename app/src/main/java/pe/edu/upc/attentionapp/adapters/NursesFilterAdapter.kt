package pe.edu.upc.attentionapp.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_nurse.view.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.controllers.activities.HomeActivity
import pe.edu.upc.attentionapp.controllers.activities.NurseProfileActivity
import pe.edu.upc.attentionapp.models.Nurse
import pe.edu.upc.attentionapp.models.Reservation
import pe.edu.upc.attentionapp.network.api.ReservationAPI
import pe.edu.upc.attentionapp.network.responses.AuthResponse
import pe.edu.upc.attentionapp.network.responses.common.PostResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NursesFilterAdapter(var nurses: List<Nurse>, var context: Context, var contract:Reservation) : RecyclerView.Adapter<NursesFilterAdapter.NurseViewHolder>(){


    private lateinit var reservationAPI:ReservationAPI

    inner class NurseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNurse = itemView.itemNurse
        val shortNameTextView = itemView.shortNameTextView
        val nurseImageView = itemView.nurseImageView
        private lateinit var sharedPreferences: SharedPreferences

        fun bindTo(nurse: Nurse){
            sharedPreferences = context.getSharedPreferences(AttentionAppConfig.SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE)
            val idCustomer= sharedPreferences.getInt(AttentionAppConfig.SHARED_PREFERENCES_FIELD_IDCUSTOMER,0)
            val token = sharedPreferences.getString(AttentionAppConfig.SHARED_PREFERENCES_FIELD_TOKEN,"")
            shortNameTextView.text = nurse.shortName
            Picasso
                .get()
                .load(nurse.thumbnailImage)
                .placeholder(R.drawable.ic_user_placeholder_48dp)
                .into(nurseImageView)

            contract.idCustomer=idCustomer
            contract.idNurse=nurse.idnurse
            contract.idCard=2
            itemNurse.setOnClickListener{

                val builder = AlertDialog.Builder(context)


                builder.setTitle("¿Deseas contratar ${nurse.shortName} ?")
                builder.setMessage("Click en sí para confirmar")
                builder.setPositiveButton("Sí"){dialog, which ->

                    val registerCall=reservationAPI.save("Bearer $token",contract)

                    registerCall.enqueue(object: Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                            if(response.isSuccessful){
                                    Toast.makeText(context,"Enfermera(o) contratado",Toast.LENGTH_SHORT).show()
                                    val intent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(intent)
                            }
                            else{
                                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                            }

                        }
                    })

                }

                builder.setNegativeButton("No"){dialog,which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NurseViewHolder {
        return NurseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nurse,parent,false))
    }

    override fun onBindViewHolder(holder: NurseViewHolder, position: Int) {
        val retrofit= Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        reservationAPI=retrofit!!.create<ReservationAPI>(ReservationAPI::class.java)
        holder.bindTo(nurses[position])
    }

    override fun getItemCount(): Int {
        return nurses.size
    }
}