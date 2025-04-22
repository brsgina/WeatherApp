package hu.gina.tkweatherapp.apiservice

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cookies.HttpCookies

actual fun createHttpClient(): HttpClient = HttpClient(Android) {
    engine {
        connectTimeout = 100_000
        socketTimeout = 100_000
    }
    install(HttpCookies)
}