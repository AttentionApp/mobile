package pe.edu.upc.attentionapp.controllers.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cards.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.adapters.CardsAdapter
import pe.edu.upc.attentionapp.models.Card
import pe.edu.upc.attentionapp.network.api.CardAPI
import pe.edu.upc.attentionapp.network.api.CustomersAPI
import pe.edu.upc.attentionapp.network.responses.common.CollectionResponse
import pe.edu.upc.attentionapp.util.AttentionAppConfig
import pe.edu.upc.attentionapp.util.AttentionAppConfig.Companion.SHARED_PREFERENCES_FIELD_IDCUSTOMER
import pe.edu.upc.attentionapp.util.AttentionAppConfig.Companion.SHARED_PREFERENCES_FIELD_TOKEN
import pe.edu.upc.attentionapp.util.AttentionAppConfig.Companion.SHARED_PREFERENCES_NAME
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var adapter : CardsAdapter
    var cards: List<Card> = ArrayList<Card>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        getCards()
        rvCSCards.layoutManager=LinearLayoutManager(this)

    }

    private fun getCards() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AttentionAppConfig.API_V1_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cardAPI = retrofit.create(CustomersAPI::class.java)
        val token = sharedPreferences.getString(SHARED_PREFERENCES_FIELD_TOKEN,"")
        val idCustomer= sharedPreferences.getInt(SHARED_PREFERENCES_FIELD_IDCUSTOMER,0)
        val cardCall = cardAPI.getCards("Bearer $token", idCustomer)



        cardCall.enqueue(object: Callback<CollectionResponse<Card>> {
            override fun onFailure(call: Call<CollectionResponse<Card>>, t: Throwable) {
                Log.d("Exception: ", t.toString())
            }

            override fun onResponse(
                call: Call<CollectionResponse<Card>>,
                response: Response<CollectionResponse<Card>>
            ) {
                if(response.isSuccessful){
                    cards = response.body()!!.rows
                    adapter = CardsAdapter(cards,this@CardsActivity)
                    //Toast.makeText(this@CardsActivity,adapter.itemCount.toString(),Toast.LENGTH_SHORT).show()
                    rvCSCards.adapter = adapter
                }
            }

        })
    }


}
