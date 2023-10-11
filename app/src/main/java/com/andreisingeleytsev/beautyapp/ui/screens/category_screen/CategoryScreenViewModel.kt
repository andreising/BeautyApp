package com.andreisingeleytsev.beautyapp.ui.screens.category_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.beautyapp.R
import com.andreisingeleytsev.beautyapp.data.DataStoreRepository
import com.andreisingeleytsev.beautyapp.ui.utils.Category
import com.andreisingeleytsev.beautyapp.ui.utils.CategoryConverter
import com.andreisingeleytsev.beautyapp.ui.utils.Routes
import com.andreisingeleytsev.beautyapp.ui.utils.Tip
import com.andreisingeleytsev.danceclasses.ui.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryScreenViewModel@Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    savedStateHandle: SavedStateHandle?
):ViewModel() {

    private val _uiEvent = Channel<UIEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: CategoryScreenEvent) {
        when (event) {
            is CategoryScreenEvent.OnClick -> {
                val index = list.value.indexOf(event.tip)
                val string = if (indexesList.isNotEmpty()) indexesList[index].toCharArray()
                else (id.toString() + list.value.indexOf(event.tip).toString()).toCharArray()
                val category = string[0].toString()
                val tip = string[1].toString()
                sendUIEvent(UIEvents.Navigate(Routes.TIP_SCREEN + "/$category"+"/$tip"))
            }
        }
    }

    private fun sendUIEvent(event: UIEvents) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private var indexesList = listOf<String>()
    val title = mutableStateOf(R.string.categories)
    val list = mutableStateOf(listOf<Tip>())
    private var id = 1
    init {
        id = savedStateHandle?.get<String>("category_id")?.toInt() ?: 1
        val category = if (id!=5) CategoryConverter.fromIndexToCategory(id)
        else null
        if (category!=null) {
            title.value = category.stringRes
            list.value = category.tips
        } else {
            title.value = R.string.favourites
            viewModelScope.launch {
                dataStoreRepository.readFavourites().collect{set->
                    indexesList = set.toList()
                    val firstList = mutableListOf<Tip>()
                    set.forEach {
                        val array = it.toCharArray()
                        firstList.add(
                            CategoryConverter.fromIndexToCategory(
                                array[0].toString().toInt()
                            ).tips[array[1].toString().toInt()]
                        )
                    }
                    list.value = firstList
                }
            }
        }
    }
}