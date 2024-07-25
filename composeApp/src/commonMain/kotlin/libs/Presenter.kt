package libs

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

interface Presenter<M, E> {
  @Composable
  fun present(lastModel: M, events: Flow<E>): M
}