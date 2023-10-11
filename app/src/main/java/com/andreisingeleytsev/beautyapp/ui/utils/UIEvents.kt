package com.andreisingeleytsev.danceclasses.ui.utils

sealed class UIEvents(){
    data class Navigate(val route: String):UIEvents()
}