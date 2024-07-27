package di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
  private val creators: Map<KClass<out ViewModel>, () -> ViewModel>
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