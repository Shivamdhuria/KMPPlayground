import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.abc.library.domain.Dog
import me.abc.library.interactors.GetDogs
import me.abc.library.network.DogServiceImpl
import me.abc.library.network.models.DogDtoMapper
import org.jetbrains.skia.impl.Log


fun main() = application {
    val windowState = rememberWindowState()

    val scope = rememberCoroutineScope()
    val dogs = mutableListOf<Dog>()
    val getDogs = GetDogs(DogServiceImpl(), dtoMapper = DogDtoMapper())

    Log.error("emite")
    LaunchedEffect(true) {
        getDogs.execute().onEach {
            Log.error("emite")
        }
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "People In Space"
    ) {

        Box() {
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                Text("hcjkhdkjahdjkhdajksh")
                DogList(dogs = dogs)
            }

            Column() {
                Spacer(Modifier.weight(1f))
                Button(onClick = {
                    scope.launch {
                        getDogs.execute().onEach { dataState ->
//                                         loading.value = dataState.loading

                            dataState.data?.let { list ->
                                dogs.addAll(list)
                            }

//                                         dataState.error?.let { error ->
//                                             Log.e("get", "get dogs: ${error}")
//                                         }

                        }
                    }

                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Load More")
                }
            }
        }

    }
}


@ExperimentalCoroutinesApi
@Composable
fun DogList(dogs: List<Dog>) {
//    Column {
//        dogs.forEach { DogItemCard(it, onClick = {}) }
//    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = dogs,
            itemContent = {
                PuppyListItem(dog = it)
            })
    }
}

@Composable
fun PuppyListItem(dog: Dog) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        elevation = 2.dp,

        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
//                val image = loadPicture(url = dog.imageUrl).value
//
//                image?.asImageBitmap()?.let {
//                    Image(
//                        bitmap = it,
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .size(84.dp)
//                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
//                    )
//                }


            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = dog.breed, style = MaterialTheme.typography.h6)
                Text(text = dog.rating, style = MaterialTheme.typography.caption)

            }
        }

    }
}