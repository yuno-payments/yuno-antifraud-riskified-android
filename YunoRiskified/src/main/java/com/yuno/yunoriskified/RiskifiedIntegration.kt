package com.yuno.yunoriskified

import android.app.Application
import android.content.Context
import com.riskified.android_sdk.RiskifiedBeaconMain
import com.riskified.android_sdk.RiskifiedBeaconMainInterface

/**
 * Inicializa la librería YunoRiskified.
 *
 * Esta función debe ser llamada una vez en la aplicación de Android para
 * inicializar la librería Riskified. Esta función toma dos argumentos de
 * cadena: "shopName", que es el nombre de la tienda y "token", que es el
 * token de la API de Riskified.
 *
 * @param shopName El nombre de la tienda.
 * @param token El token de la API de Riskified.
 */
private val RXBeacon = RiskifiedBeaconMain()

fun Application.initYunoRiskified(shopName: String, token: String) {
    RXBeacon.startBeacon(shopName, token, false, this.applicationContext)
}

/**
 * Obtiene el ID de sesión de YunoRiskified.
 *
 * Esta función se puede llamar para obtener el ID de sesión de Riskified.
 * Esta función devuelve el valor del cookie de sesión de Riskified que se
 * guarda en la instancia de RiskifiedBeaconMain llamada RXBeacon.
 *
 * @return El ID de sesión o una cadena vacía si no se ha obtenido.
 */
fun Context.onCreateYunoRiskified(): String {
    val sessionId = RXBeacon.rCookie()
    return if (sessionId.isEmpty() || sessionId == null) {
        ""
    } else {
        sessionId
    }
}
