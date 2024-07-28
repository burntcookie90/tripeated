package navigation.auth

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import di.injectedViewModel
import libs.ViewModelScreen
import navigation.destinations.RootDestination
import navigation.destinations.TripeatedDestination

interface AuthenticatedDestination {
  val navGraphBuilder: NavGraphBuilder
  val navController: NavController
  val onLoggedOut: (Boolean) -> Unit
}

data class AuthenticatedDestinationImpl(
  override val navGraphBuilder: NavGraphBuilder,
  override val navController: NavController,
  override val onLoggedOut: (Boolean) -> Unit,
) : AuthenticatedDestination

public inline fun <reified T : TripeatedDestination> AuthenticatedDestination.screen(
  crossinline content: @Composable T.(NavBackStackEntry) -> Unit,
) {
  navGraphBuilder.screen<T>(
    onLoggedOut,
    navController,
    content = {
      content(it.toRoute(), it)
    },
  )
}

inline fun <reified D: TripeatedDestination> NavGraphBuilder.screen(
  crossinline onLoggedOut: (Boolean) -> Unit,
  navController: NavController,
  crossinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
  composable<D> {
    val loggedInGraph = remember(it) {
      navController.getBackStackEntry(RootDestination.LoggedIn)
    }

    ViewModelScreen(
      viewModel = injectedViewModel<UserLoggedInViewModel>(key = loggedInGraph.toString()),
      tag = "authenticated composable destination"
    ) {

      LaunchedEffect(key1 = model.state) {
        when (model.state) {
          AuthState.LOADING,
          AuthState.LOGGED_IN,
          -> {
          }

          AuthState.LOGGED_OUT -> onLoggedOut(false)
        }
      }
      when (model.state) {
        AuthState.LOGGED_OUT,
        AuthState.LOGGED_IN -> content(it)
        AuthState.LOADING -> {
          Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
          }
        }
      }
    }
  }
}

