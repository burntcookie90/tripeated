package navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import di.AppComponent

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

//public inline fun <reified T : TripeatedDestination> AuthenticatedDestination.screen(
//  deepLinks: List<NavDeepLink> = emptyList(),
//  noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { EnterTransition.None },
//  noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { ExitTransition.None },
//  noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
//  noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
//  noinline content: @Composable T.(NavBackStackEntry) -> Unit,
//) {
//  navGraphBuilder.screen<T>(
//    onLoggedOut,
//    navController,
//    deepLinks,
//    enterTransition,
//    exitTransition,
//    popEnterTransition,
//    popExitTransition,
//    content,
//  )
//}
//
//public inline fun <reified T : TripeatedDestination> NavGraphBuilder.screen(
//  noinline onLoggedOut: (Boolean) -> Unit,
//  navController: NavController,
//  deepLinks: List<NavDeepLink> = emptyList(),
//  noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { EnterTransition.None },
//  noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { ExitTransition.None },
//  noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
//  noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
//  noinline content: @Composable T.(NavBackStackEntry) -> Unit,
//) {
//  val serializer = serializer<T>()
//  registerDestinationType(T::class, serializer)
//  screen(
//    route = createRoutePattern(serializer),
//    arguments = createNavArguments(serializer),
//    onLoggedOut = onLoggedOut,
//    navController = navController,
//    deepLinks = deepLinks,
//    enterTransition = enterTransition,
//    exitTransition = exitTransition,
//    popEnterTransition = popEnterTransition,
//    popExitTransition = popExitTransition,
//  ) {
//    val t = decodeArguments(serializer, it)
//    if (t is TrackableDestination) {
//      t.TrackScreenEffect()
//    }
//    t.content(it)
//  }
//}
//
//fun NavGraphBuilder.screen(
//  route: String,
//  onLoggedOut: (Boolean) -> Unit,
//  navController: NavController,
//  arguments: List<NamedNavArgument> = emptyList(),
//  deepLinks: List<NavDeepLink> = emptyList(),
//  enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { EnterTransition.None },
//  exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { ExitTransition.None },
//  popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
//  popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
//  content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
//) {
//  composable(
//    route,
//    arguments,
//    deepLinks,
//    enterTransition,
//    exitTransition,
//    popEnterTransition,
//    popExitTransition,
//  ) {
//    val loggedInGraph = remember(it) {
//      navController.getBackStackEntry(createRoutePattern<RootDestinations.LoggedIn>())
//    }
//
//    ViewModelScreen(
//      viewModel = hiltViewModel<UserLoggedInViewModel>(loggedInGraph),
//      tag = "authenticated composable destination",
//    ) {
//
//      LaunchedEffect(key1 = model.state) {
//        when (model.state) {
//          AuthState.LOADING,
//          AuthState.LOGGED_IN,
//          -> {
//          }
//
//          AuthState.LOGGED_OUT -> onLoggedOut(false)
//          AuthState.VOLUNTARY_LOGGED_OUT -> onLoggedOut(true)
//        }
//      }
//      when (model.state) {
//        AuthState.LOGGED_OUT,
//        AuthState.VOLUNTARY_LOGGED_OUT,
//        -> {
//        }
//
//        AuthState.LOGGED_IN -> content(it)
//        AuthState.LOADING -> {
//          Box(modifier = Modifier.fillMaxSize()) {
//            CircularProgressIndicator(modifier = Modifier.size(48.dp))
//          }
//        }
//      }
//    }
//  }
//}
//
