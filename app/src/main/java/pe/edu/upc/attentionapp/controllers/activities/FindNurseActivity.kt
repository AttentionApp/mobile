package pe.edu.upc.attentionapp.controllers.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_find_nurse.*
import pe.edu.upc.attentionapp.R
import pe.edu.upc.attentionapp.models.Reservation
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.*

class FindNurseActivity : AppCompatActivity() {


    private lateinit var dateStart:Date
    private lateinit var dateEnd:Date
    private var startHour: Int = 0
    private var endHour: Int = 0
    private var year:Int = 0
    private var month:Int = 0
    private var day:Int = 0
    private var amount:Int=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_nurse)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        datePicker()
        reserveHour()
        numberOfHours()
        ok()

    }

    fun datePicker(){
        val calendar= Calendar.getInstance()
        this.year=calendar.get(Calendar.YEAR)
        this.month =calendar.get(Calendar.MONTH)
        this.day=calendar.get(Calendar.DAY_OF_MONTH)

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

            calculateHours()

            var reservation:Reservation = Reservation()

            reservation.startDate=this.dateStart
            reservation.endDate=this.dateEnd
            reservation.amount=this.amount

            val calendar= Calendar.getInstance()
            val tempYear=calendar.get(Calendar.YEAR)
            val tempMonth =calendar.get(Calendar.MONTH)
            val tempDay=calendar.get(Calendar.DAY_OF_MONTH)



            //if(this.year > tempYear && this.month > tempMonth && this.day>tempDay){

                val intent = Intent(this,ContractActivity::class.java)
                intent.putExtra("contract",reservation)
                startActivity(intent)

           /* }else{
                Toast.makeText(this@FindNurseActivity,"Seleccionar un dia correcto para contratar",Toast.LENGTH_SHORT).show()
            }*/

        }
    }


    fun reserveHour(){
        var hourTemp = 0
        val options = arrayOf("7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00")

        spFNReserveHour.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        spFNReserveHour.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val endIndex = options[p2].indexOf(":")
                val strHour = options[p2].substring(0,endIndex)
                startHour = strHour.toInt()
                //Toast.makeText(this@FindNurseActivity,strHour,Toast.LENGTH_SHORT).show()
            }

        }
    }


    fun numberOfHours(){
        val options = arrayOf("2 Horas","3 Horas","4 Horas","5 Horas","6 Horas")

        spFNNumberHours.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        spFNNumberHours.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val endIndex = options[p2].indexOf(" ")
                val strHour = options[p2].substring(0,endIndex)
                endHour=strHour.toInt()
                //Toast.makeText(this@FindNurseActivity,p2.toString(),Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun calculateHours(){
        var tempStartDate = Date(2019,month,day,startHour,0)
        var tempEndDate = Date(2019,month,day,startHour+endHour,0)

        this.dateStart=tempStartDate
        this.dateEnd=tempEndDate
        this.amount = endHour * 20

    }


}

