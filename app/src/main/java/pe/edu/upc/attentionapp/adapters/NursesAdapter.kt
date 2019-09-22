package pe.edu.upc.attentionapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_nurse.view.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.models.Nurse

class NursesAdapter(var nurses: List<Nurse>) : RecyclerView.Adapter<NursesAdapter.NurseViewHolder>(){

    inner class NurseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNurse = itemView.itemNurse
        val shortNameTextView = itemView.shortNameTextView
        val nurseImageView = itemView.nurseImageView
        fun bindTo(nurse: Nurse){
            shortNameTextView.text = nurse.shortName
            itemNurse.setOnClickListener{
                TODO("not implemented")
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