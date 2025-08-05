package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.Channels
import java.io.File
import java.nio.channels.FileChannel
import java.nio.file.StandardOpenOption
import kotlinx.coroutines.launch

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(
                """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>API Button</title>
                </head>
                <body>
                    <h1>Test</h1>
                    <button id="fetchButton">Frost Test</button>
                    <button id="fetchButton2">Rim Test</button>
                    
                    <script>
                        document.getElementById("fetchButton").addEventListener("click", function() {
                            fetch("/fetchData");
                        });

                        document.getElementById("fetchButton2").addEventListener("click", function() {
                            fetch("/fetchData2");
                        });
                    </script>
                </body>
                </html>
                """, contentType = io.ktor.http.ContentType.Text.Html
            )
        }

        val frost = FrostApi()
        val cclient = CustomHttpClient()

        get("/fetchData") {
            val jsonResponse = frost.fetchFrostData(
                cclient,
                //60.95, 4.20, 23.0, // in the sea to the west close to land
                //58.41, 5.92, 23.0, // egersund, 3/4 of quadrants should be filled only
                //60.77, 4.69, 23.0, // westmost region
                59.91, 10.75, 23.0, // oslo
                //60.791071, 4.665847, 23.0, // fedje
                //61.036342, 4.501887, 23.0, // skardsooyna
                //60.329506, 4.892789, 23.0, // fjellooya
                //59.309690, 4.866958, 0.0, // utsira
                //62.00, 9.52, 3000.0, // Dovre Nasjonalpark
                //17.862732, 17.365775, 23.0, // tchad
                listOf("mean(air_temperature P1M)", "mean(snow_coverage_type P1M)", "mean(cloud_area_fraction P1M)")
            )
            call.respond(jsonResponse)
        }

        get("/fetchData2") {
            val jsonResponse = frost.fetchRimData(
                cclient,
                59.91, 10.75,
                "mean(surface_downwelling_shortwave_flux_in_air PT1H)"
            )
            call.respond(jsonResponse)
        }
    }
}



