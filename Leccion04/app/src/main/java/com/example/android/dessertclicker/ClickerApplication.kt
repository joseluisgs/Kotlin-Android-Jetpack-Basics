package com.example.android.dessertclicker

import android.app.Application
import timber.log.Timber

/**
 * Clase que hereda de Application para que se pueda acceder a los datos de la aplicación desde cualquier lugar
 */
class ClickerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicializar Timber
        Timber.plant(Timber.DebugTree())
    }
}
