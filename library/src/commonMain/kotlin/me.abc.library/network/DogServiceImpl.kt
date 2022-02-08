package me.abc.library.network

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import me.abc.library.network.models.DogDto

class DogServiceImpl: DogService {

    private val BASE_URL = "https://dog.ceo/api"

    private val client: HttpClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getDogs(): DogDto {
        return client.get<DogDto>("$BASE_URL/breeds/image/random/4"){
        }
    }
}



