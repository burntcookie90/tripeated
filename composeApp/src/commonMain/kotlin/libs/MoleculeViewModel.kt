package libs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class MoleculeViewModel<State, Event>(
  initialState: State,
  presenter: @Composable (lastModel: State, events: Flow<Event>) -> State,
  sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5.seconds),
) : ViewModel() {
  var tag: String? = null
  private val scope = CoroutineScope(viewModelScope.coroutineContext + Dispatchers.Main)

  constructor(
    initialState: () -> State,
    presenter: @Composable (lastModel: State, events: Flow<Event>) -> State,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(5.seconds),
  ) : this(
    initialState = initialState(),
    presenter = presenter,
    sharingStarted = sharingStarted,
  )

  /**
   * Events have a capacity large enough to handle simultaneous UI events, but small enough to surface issues if they
   * get backed up for some reason.
   * The channel will be re-used across instances where the SharedFlow is turned cold again after there were no
   * collectors according to [sharingStarted]. The new Presenter launched by the new StateFlow instance will then be
   * responsible of consuming the unconsumed channel [Event]s.
   */
  private val events: Channel<Event> = Channel<Event>(
    capacity = 20,
    onBufferOverflow = BufferOverflow.SUSPEND,
  )
  private val eventsFlow: Flow<Event> = events.receiveAsFlow()

  private var lastState: State = initialState

  fun emit(event: Event) {
    if (tag != null) {
      println("$tag: Event received $event")
    }
    if (events.trySend(event).isFailure) {
      error("Event buffer overflow on event:$event.")
    }
  }

  val uiState: StateFlow<State> = moleculeFlow(RecompositionMode.Immediate) {
    presenter(lastState, eventsFlow)
  }.onEach { newState: State ->
    lastState = newState
    if (tag != null) {
      println("$tag: Emitting new model: $newState")
    }
  }.stateIn(
    scope = scope,
    started = sharingStarted,
    initialValue = lastState,
  )

}

interface MoleculeViewModelScope<M, E> {
  val model: M
  val dispatch: (E) -> Unit
}

private data class MoleculeViewModelScopeImpl<M, E>(
  override val model: M,
  override val dispatch: (E) -> Unit,
) : MoleculeViewModelScope<M, E>

/**
 * Used to reduce the boilerplate at screen creation
 */
@Composable
fun <M, E, V : MoleculeViewModel<M, E>> ViewModelScreen(
  viewModel: V,
  tag: String? = null,
  content: @Composable MoleculeViewModelScope<M, E>.() -> Unit,
) {
  viewModel.tag = tag
  val model by viewModel.uiState.collectAsStateMultiplatform()
  val scope: MoleculeViewModelScope<M, E> = MoleculeViewModelScopeImpl(
    model = model,
    dispatch = viewModel::emit,
  )
  content(scope)
}

@Composable
fun <M, E, V : MoleculeViewModel<M, E>> NavBackStackEntry.ViewModelScreen(
  viewModel: V,
  content: @Composable MoleculeViewModelScope<M, E>.() -> Unit,
) {
  ViewModelScreen(
    viewModel = viewModel,
    tag = toRoute(),
    content = content,
  )
}

@Composable
expect fun <T> StateFlow<T>.collectAsStateMultiplatform(
  context: CoroutineContext = EmptyCoroutineContext,
): State<T>