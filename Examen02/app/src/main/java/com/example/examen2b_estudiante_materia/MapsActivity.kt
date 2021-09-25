package com.example.examen2b_estudiante_materia

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.examen2b_estudiante_materia.dto.EstudianteDto
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val itemId = intent.getParcelableExtra<EstudianteDto>("id")

        val textViewNombreUbicacion = findViewById<TextView>(R.id.tvNombreUbicacion)
        textViewNombreUbicacion.text = "Ubicación de " + itemId?.nombre

        solicitarPermisos()

        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync {
            mapa=it
            establecerConfiguracionMapa()
            val latitud = -0.145522
            val longitud = -78.503101

           val ubicacion = LatLng(latitud, longitud)
            val zoom = 14f
           anadirMarcador(ubicacion, itemId?.nombre.toString())
            moverCamaraConZoom(ubicacion, zoom)
        }
    }
    fun anadirMarcador(latLng: LatLng, title: String) {
        mapa.addMarker(MarkerOptions().position(latLng).title(title))
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float) {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_FINE_LOCATION)
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }


    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (!tienePermisos) {
            ActivityCompat.requestPermissions(
                this, arrayOf(//Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), 1 //cODIGO DE PETICION DE LOS PERMISOS
            )
        }
    }
}