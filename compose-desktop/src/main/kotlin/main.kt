import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


fun main() = application {
    val windowState = rememberWindowState()

//    var peopleState by remember { mutableStateOf(emptyList<Assignment>()) }
//    var selectedPerson by remember { mutableStateOf<Assignment?>(null) }
//
//    val peopleInSpaceApi = koin.get<PeopleInSpaceApi>()
//
//    LaunchedEffect(true) {
//        peopleState = peopleInSpaceApi.fetchPeople().people
//        selectedPerson = peopleState.first()
//    }

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