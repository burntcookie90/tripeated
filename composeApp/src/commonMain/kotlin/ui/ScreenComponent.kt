package ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

interface ScreenComponent<V: ViewModel> {
  val viewModelFactory: () -> V
}

@Composable
inline fun <reified V: ViewModel> ScreenComponent<V>.viewModel() =
  androidx.lifecycle.viewmodel.compose.viewModel { viewModelFactory() }

