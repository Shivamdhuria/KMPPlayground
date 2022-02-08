package me.abc.library.network

import me.abc.library.network.models.DogDto

interface DogService {

    suspend fun getDogs(): DogDto


}