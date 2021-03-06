package me.abc.android.presentation.ui.dog_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.abc.android.presentation.components.DogItemCard
import me.abc.android.presentation.components.PuppyListItem
import me.abc.android.presentation.theme.PawsTheme
import me.abc.library.domain.Dog

@Composable
fun DogListScreen(viewModel: DogListViewModel, navController: NavController) {

    val dogs = viewModel.recipes.value

    PawsTheme() {
        Box() {
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                DogList(dogs = dogs)
            }

            Column() {
                Spacer(Modifier.weight(1f))
                Button(onClick = { viewModel.loadMore() }, modifier = Modifier.fillMaxWidth()) {
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