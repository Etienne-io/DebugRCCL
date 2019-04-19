package io.mapwize.rccldebug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import io.mapwize.mapwizeformapbox.api.Api
import io.mapwize.mapwizeformapbox.api.ApiCallback
import io.mapwize.mapwizeformapbox.api.OfflineManager
import io.mapwize.mapwizeformapbox.api.Venue
import io.mapwize.mapwizeformapbox.map.MapOptions
import io.mapwize.mapwizeformapbox.map.MapwizePlugin
import io.mapwize.mapwizeformapbox.map.MapwizePluginFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var mapboxMap:MapboxMap? = null
    var mapwize:MapwizePlugin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mapwize = MapwizePluginFactory.create(mapView, MapOptions.Builder().build())

        mapView.getMapAsync {
            mapboxMap = it
            Log.i("Debug", "Mapbox map async")
            it.setStyle(Style.Builder().fromUrl(DebugApplication.MAPBOX_STYLE_URL)) {

                Log.i("Debug", "Style loaded")

            }
        }

        mapwize?.addOnClickListener {
            mapboxMap?.cameraPosition = CameraPosition.Builder().zoom(19.0).build()
        }


        val om = OfflineManager(this, DebugApplication.MAPBOX_STYLE_URL)

        Api.getVenue(DebugApplication.MAPWIZE_VENUE_ID, object : ApiCallback<Venue> {
            override fun onSuccess(p0: Venue?) {
                om.downloadData(p0!!, p0!!.defaultUniverse, object :OfflineManager.DownloadTaskListener {
                    override fun onSuccess() {
                        Log.i("Debug", "Success")
                    }

                    override fun onFailure(p0: Exception?) {
                        Log.i("Debug", "Failure")
                    }

                    override fun onProgress(p0: Int) {
                        Log.i("Debug", "Progress")
                    }

                })
            }

            override fun onFailure(p0: Throwable?) {

            }

        })

    }
}
