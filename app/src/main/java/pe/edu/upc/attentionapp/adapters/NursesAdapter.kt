package pe.edu.upc.attentionapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_nurse.view.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.controllers.activities.NurseProfileActivity
import pe.edu.upc.attentionapp.models.Nurse

class NursesAdapter(var nurses: List<Nurse>, var context: Context) : RecyclerView.Adapter<NursesAdapter.NurseViewHolder>(){

    inner class NurseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNurse = itemView.itemNurse
        val shortNameTextView = itemView.shortNameTextView
        val nurseImageView = itemView.nurseImageView
        fun bindTo(nurse: Nurse){
            shortNameTextView.text = nurse.shortName
            Picasso
                .get()
                .load(nurse.thumbnailImage)
                .placeholder(R.drawable.ic_user_placeholder_48dp)
                .into(nurseImageView)
            itemNurse.setOnClickListener{
                val intent = Intent(context,NurseProfileActivity::class.java)
                intent.putExtra("nurseObject",nurse)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NurseViewHolder {
        return NurseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nurse,parent,false))
    }

    override fun onBindViewHolder(holder: NurseViewHolder, position: Int) {
        holder.bindTo(nurses[position])
    }

    override fun getItemCount(): Int {
        return nurses.size
    }
}