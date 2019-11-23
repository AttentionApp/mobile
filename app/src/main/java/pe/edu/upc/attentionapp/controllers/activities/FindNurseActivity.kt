package pe.edu.upc.attentionapp.controllers.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_find_nurse.*
import pe.edu.upc.attentionapp.R
import java.util.*

class FindNurseActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    private var reservationLatidude: Double? = null
    private var reservationLongitude: Double? = null
    
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_nurse)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        datePicker()
        ok()
        reserveHour()
        numberOfHours()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.clear()
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMapClickListener(this)
        map.setOnMarkerClickListener{ false }
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18.0f))
            }
        }
        setUpMap()
    }

    override fun onMapClick(point: LatLng) {
        map.clear()
        map.addMarker(MarkerOptions().position(point))
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }

    fun datePicker(){
        val calendar= Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)

         btFNPickDate.setOnClickListener {
            val datePick=
                DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                    tvFNDate.text = ""+mDay+"/"+(mMonth+1)+"/"+mYear
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
