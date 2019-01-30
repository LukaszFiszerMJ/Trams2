package com.fatapps.trams

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fatapps.domain.interactor.trams.FetchTramStopsUseCase
import com.fatapps.domain.model.Stop

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.GoogleMap.OnCircleClickListener
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS




class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * apiKey = 54102799-ea3d-4b42-9d7f-65ea8d54a6ac
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mainViewModel.getTramStops().observe(this, Observer {
            mMap.clear()


            it.forEach {

                Timber.d("Add stop ${it.name}")

                val circle = mMap.addCircle(
                    CircleOptions()
                        .center(LatLng(it.lat, it.lon))
                        .radius(10.0)
                        .strokeColor(Color.BLUE)
                        .fillColor(Color.BLUE)
                        .clickable(true)
                )

                circle.tag = it

                mMap.setOnCircleClickListener{ circle ->
                    val stop = circle.tag
                    val stop2 =stop as Stop
                    TimeTableActivity.open(this, stop2.stopId, stop2.name)
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(it.lat, it.lon)))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lon),14F))

            }
        })


    }
}
