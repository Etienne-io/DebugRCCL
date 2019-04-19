package io.mapwize.rccldebug


import android.app.Application
import com.mapbox.mapboxsdk.Mapbox
import io.mapwize.mapwizeformapbox.AccountManager

class DebugApplication: Application() {

    companion object {
        val MAPWIZE_API_KEY = "YOUR_MAPWIZE_API_KEY"
        val MAPBOX_TOKEN = "YOUR_MAPBOX_TOKEN"
        val MAPBOX_STYLE_URL = "YOUR_MAPBOX_STYLE_URL"
        val MAPWIZE_VENUE_ID = "YOUR_VENUE_ID"
    }

    override fun onCreate() {
        super.onCreate()
        AccountManager.start(this, MAPWIZE_API_KEY)
        Mapbox.getInstance(this, MAPBOX_TOKEN)
    }

}