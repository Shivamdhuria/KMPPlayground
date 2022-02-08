import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


fun main() = application {
    val windowState = rememberWindowState()




    LaunchedEffect(true) {
//        peopleState = peopleInSpaceApi.fetchPeople().people
//        selectedPerson = peopleState.first()
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "People In Space"
    ) {

        Row(Modifier.fillMaxSize()) {

            Box(Modifier.width(250.dp).fillMaxHeight().background(color = Color.LightGray)) {

            }

            Spacer(modifier = Modifier.width(1.dp).fillMaxHeight())

            Box(Modifier.fillMaxHeight()) {

            }
        }

    }
}

