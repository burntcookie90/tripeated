package di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
  private val creators: Map<KClass<out ViewModel>, VmFactory<*>>
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
    val creator = creators[modelClass]
    try {
      @Suppress("UNCHECKED_CAST")
      return creator?.invoke() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}

val LocalViewModelFactory = compositionLocalOf<ViewModelFactory> {
  error("No ViewModelFactory provided")
}

@Composable
inline fun <reified V: ViewModel> injectedViewModel(key: String? = null): V {
  val factory = LocalViewModelFactory.current
  return viewModel(key = key) {
    factory.create(V::class, this)
  }
}

