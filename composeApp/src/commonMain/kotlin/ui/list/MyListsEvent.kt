package ui.list

sealed interface MyListsEvent {
  data object Logout : MyListsEvent
}