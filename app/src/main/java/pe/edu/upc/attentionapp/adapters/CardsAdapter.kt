package pe.edu.upc.attentionapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card.view.*
import kotlinx.android.synthetic.main.item_nurse.view.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.controllers.activities.NurseProfileActivity
import pe.edu.upc.attentionapp.models.Card
import pe.edu.upc.attentionapp.models.Nurse

class CardsAdapter(var cards: List<Card>, var context: Context) : RecyclerView.Adapter<CardsAdapter.CardViewHolder>(){

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCard = itemView.itemCard
        val bankName= itemView.tvICBankName
        val accountNumber = itemView.tvICAccountNumber
        fun bindTo(card: Card){
            bankName.text = card.bankName
            accountNumber.text = card.accountNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindTo(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}