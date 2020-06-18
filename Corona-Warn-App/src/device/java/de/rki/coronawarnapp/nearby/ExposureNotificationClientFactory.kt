package de.rki.coronawarnapp.nearby

import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.exposurenotification.ExposureNotificationClient
import de.rki.coronawarnapp.CoronaWarnApplication

object ExposureNotificationClientFactory {
    fun createClient(): ExposureNotificationClient {
        return Nearby.getExposureNotificationClient(CoronaWarnApplication.getAppContext())
    }
}
