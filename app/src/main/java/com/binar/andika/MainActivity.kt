package com.binar.andika

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.security.ProviderInstaller

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
            && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT
        ) {
            try {
                ProviderInstaller.installIfNeeded(this)
            } catch (e: GooglePlayServicesRepairableException) {
                GooglePlayServicesUtil.showErrorNotification(e.connectionStatusCode, this)
                return
            } catch (e: GooglePlayServicesNotAvailableException) {
                return
            }
        }
    }
}