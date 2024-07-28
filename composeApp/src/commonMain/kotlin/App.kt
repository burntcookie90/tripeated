import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import di.AppComponent
import di.LocalViewModelFactory
import di.ViewModelComponent
import di.create
import navigation.LoggedOutDestinations
import navigation.RootDestination
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.login.LoginCoordinates.login
import ui.register.RegisterScreenCoordinates.register
import ui.welcome.WelcomeScreenCoordinates.welcome

@Composable
@Preview
fun App() {
  val appComponent = remember { AppComponent::class.create(
    viewModelComponent = ViewModelComponent::class.create()
  ) }

  val navController = rememberNavController()
  CompositionLocalProvider(LocalViewModelFactory provides appComponent.viewModelComponent.viewModelFactory) {
    MaterialTheme {
      Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = rememberScaffoldState(),
        topBar = {
          TopAppBar(
            title = { Text("Tripeated") },
            navigationIcon = {
              //            if (canGoBack) {
              IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
              }
              //            }
            }
          )
        },
      ) {
        NavHost(navController, startDestination = RootDestination.LoggedOut) {
          //        val appDestinationScope = AppDestinationImpl(
          //          appComponent = appComponent,
          //          navGraphBuilder = this@NavHost,
          //          navController = navController
          //        )
          navigation<RootDestination.LoggedOut>(startDestination = LoggedOutDestinations.Welcome) {
            welcome(
              navigateToLogin = {
                navController.navigate(LoggedOutDestinations.Login)
              },
              navigateToRegister = {
                navController.navigate(LoggedOutDestinations.Register)
              }
            )
            login()
            register()
          }
        }
      }
    }
  }
}