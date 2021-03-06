package me.abc.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import me.abc.android.presentation.theme.PawsTheme
import me.abc.android.util.loadPicture
import me.abc.library.domain.Dog

@Composable
fun PuppyListItem(dog: Dog) {
    PawsTheme {
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(16.dp))

        ) {
            Row {
                val image = loadPicture(url = dog.imageUrl).value

                image?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(84.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                }


                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = dog.breed, style = typography.h6)
                    Text(text = dog.rating, style = typography.caption)

                }
            }
        }
    }
}