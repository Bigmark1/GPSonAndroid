package com.example.gpslocation

import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val selectedButton: Button = findViewById<Button?>(R.id.btns)
            selectedButton.setOnClickListener{
            fetchLocation()
        }

    }

    private fun  fetchLocation(){
        val task: Task<Location> = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED

            && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)

            != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
                    return
                }
            task.addOnSuccessListener{
                if(it != null){
                    var lat: Double = it.latitude
                    var lon: Double = it.longitude

                    Toast.makeText(applicationContext, "{$it.latitude} {$it.longitude}",
                        Toast.LENGTH_LONG).show()

                    val textLong: TextView = findViewById<TextView?>(R.id.locationLong)
                    val textLat: TextView = findViewById(R.id.locationLat)

                    textLong.setText(lon.toString())
                    textLat.setText(lat.toString())

                }
            }
    }


}