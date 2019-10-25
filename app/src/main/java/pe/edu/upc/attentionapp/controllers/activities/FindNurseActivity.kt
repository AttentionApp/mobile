package pe.edu.upc.attentionapp.controllers.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_find_nurse.*
import pe.edu.upc.attentionapp.R
import java.util.*

class FindNurseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_nurse)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        datePicker()
        ok()
        reserveHour()
        numberOfHours()


    }

    fun datePicker(){
        val calendar= Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)

         btFNPickDate.setOnClickListener {
            val datePick=
                DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                tvFNDate.setText(""+mDay+"/"+(mMonth+1)+"/"+mYear)
            },year,month,day)

            datePick.show()
        }
    }

    fun ok(){
        btFNOk.setOnClickListener {
            val intent = Intent(this,ContractActivity::class.java)
            startActivity(intent)
        }
    }


    fun reserveHour(){
        val options = arrayOf("7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00")

        spFNReserveHour.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        /*spFNReserveHour.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }*/
    }


    fun numberOfHours(){
        val options = arrayOf("2 Horas","3 Horas","4 Horas","5 Horas","6 Horas","7 Horas","8 Horas")

        spFNNumberHours.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
    }
}
