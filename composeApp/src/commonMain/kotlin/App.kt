import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import di.AppComponent
import di.create
import navigation.LoggedOutDestinations
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.login.LoginCoordinates.login

@Composable
@Preview
fun App() {
  val appComponent = remember { AppComponent::class.create(
  ) }
  val navController = rememberNavController()
  var canGoBack by remember { mutableStateOf(false) }
  MaterialTheme {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      scaffoldState = rememberScaffoldState(),
      topBar = {
        TopAppBar(
          title = { Text("Tripeated") },
          navigationIcon = {
            if (canGoBack) {
              IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
              }
            }
          }
        )
      },
    ) {
      NavHost(navController, startDestination = LoggedOutDestinations.Welcome.route) {
        composable(LoggedOutDestinations.Welcome.route) {
          canGoBack = false
          Column(modifier = Modifier.fillMaxSize()) {
            Text("welcome!")
            Button(onClick = { navController.navigate(LoggedOutDestinations.Login.route) }) {
              Text("Login")
            }

            Button(onClick = { navController.navigate(LoggedOutDestinations.Register.route) }) {
              Text("Register")
            }
          }
        }

        login(appComponent)

        composable(LoggedOutDestinations.Register.route) {
          canGoBack = true
          Column(modifier = Modifier.fillMaxSize()) {
            Text("register!")
            var email by remember {
              mutableStateOf("")
            }

            var password by remember {
              mutableStateOf("")
            }

            var shouldSubmitReg by remember {
              mutableStateOf(false)
            }

//            LaunchedEffect(shouldSubmitReg) {
//              if (shouldSubmitReg) {
//                val user = authService.register(email, password)
//                println(user)
//                shouldSubmitReg = false
//              }
//            }

            TextField(
              value = email,
              onValueChange = { email = it },
              label = { Text("Email") }
            )

            TextField(
              value = password,
              visualTransformation = PasswordVisualTransformation(),
              onValueChange = { password = it },
              label = { Text("Password") }
            )

            Button(onClick = { shouldSubmitReg = true }) {
              Text("Register")
            }
          }
        }
      }
    }
  }
}