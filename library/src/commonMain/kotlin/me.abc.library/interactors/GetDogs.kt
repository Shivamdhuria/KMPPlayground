package me.abc.library.interactors

import kotlinx.coroutines.flow.flow
import me.abc.library.domain.DataState
import me.abc.library.domain.Dog
import me.abc.library.domain.util.CommonFlow
import me.abc.library.domain.util.asCommonFlow
import me.abc.library.network.DogService
import me.abc.library.network.models.DogDtoMapper

class GetDogs(private val recipeService: DogService, private val dtoMapper: DogDtoMapper) {

    @Throws(Exception::class)
    fun execute(): CommonFlow<DataState<List<Dog>>> = flow {
        try {
            emit(DataState.loading())
            val dogs = getDogsFromNetwork()
            emit(DataState.success(dogs))
        } catch (e: Exception) {
            emit(DataState.error<List<Dog>>(e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()

     suspend fun getDogsFromNetwork(): List<Dog> {
        val dogDtos = recipeService.getDogs().message
        return dtoMapper.toDomainList(dogDtos)
    }
}