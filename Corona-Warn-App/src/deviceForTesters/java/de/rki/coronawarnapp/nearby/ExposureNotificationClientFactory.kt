package de.rki.coronawarnapp.nearby

import android.util.Log
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.internal.ApiKey
import com.google.android.gms.nearby.exposurenotification.ExposureConfiguration
import com.google.android.gms.nearby.exposurenotification.ExposureInformation
import com.google.android.gms.nearby.exposurenotification.ExposureNotificationClient
import com.google.android.gms.nearby.exposurenotification.ExposureSummary
import com.google.android.gms.nearby.exposurenotification.TemporaryExposureKey
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.io.File

object ExposureNotificationClientFactory {
    fun createClient(): ExposureNotificationClient {
        return MockExposureNotificationClient()
    }
}

private class MockExposureNotificationClient : ExposureNotificationClient {
    companion object {
        private const val TAG = "MockExposureNotificationClient"
    }

    private var enabled: Boolean = false

    override fun isEnabled(): Task<Boolean> {
        Log.w(TAG, "isEnabled()")

        return Tasks.forResult(enabled)
    }

    override fun provideDiagnosisKeys(
        p0: MutableList<File>?,
        p1: ExposureConfiguration?,
        p2: String?
    ): Task<Void> {
        Log.w(TAG, "provideDiagnosisKeys()")

        return Tasks.forResult(null)
    }

    override fun getExposureSummary(p0: String?): Task<ExposureSummary> {
        Log.w(TAG, "getExposureSummary()")

        val builder = ExposureSummary.ExposureSummaryBuilder()

        builder.setAttenuationDurations(intArrayOf(
            50,
            10,
            5
        ))
        builder.setDaysSinceLastExposure(1)
        builder.setMatchedKeyCount(1)
        builder.setMaximumRiskScore(50)
        builder.setSummationRiskScore(70)

        return Tasks.forResult(builder.build())
    }

    override fun start(): Task<Void> {
        Log.w(TAG, "start()")

        enabled = true

        return Tasks.forResult(null)
    }

    override fun stop(): Task<Void> {
        Log.w(TAG, "stop()")

        enabled = false

        return Tasks.forResult(null)
    }

    override fun getExposureInformation(p0: String?): Task<MutableList<ExposureInformation>> {
        Log.w(TAG, "getExposureInformation()")

        return Tasks.forResult(null)
    }

    override fun getApiKey(): ApiKey<Api.ApiOptions.NoOptions> {
        throw NotImplementedError()
    }

    override fun getTemporaryExposureKeyHistory(): Task<MutableList<TemporaryExposureKey>> {
        Log.w(TAG, "getTemporaryExposureKeyHistory()")

        return Tasks.forResult(mutableListOf())
    }
}
