package libs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface Presenter<M, E> {
  @Composable
  fun present(lastModel: M, events: Flow<E>): M
}

@Composable
fun <M, E> Presenter<M, E>.ConsumeEvents(
  events: Flow<E>,
  update: suspend CoroutineScope.(E) -> Unit
) {
  LaunchedEffect(Unit) {
    events.collect { update(this, it) }
  }
}