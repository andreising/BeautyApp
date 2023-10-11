package com.andreisingeleytsev.beautyapp.ui.screens.tip_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.beautyapp.R
import com.andreisingeleytsev.beautyapp.data.DataStoreRepository
import com.andreisingeleytsev.beautyapp.ui.screens.category_screen.CategoryScreenEvent
import com.andreisingeleytsev.beautyapp.ui.utils.CategoryConverter
import com.andreisingeleytsev.beautyapp.ui.utils.Routes
import com.andreisingeleytsev.danceclasses.ui.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipScreenViewModel@Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    savedStateHandle: SavedStateHandle?
) : ViewModel() {

    fun onEvent(event: TipScreenEvents) {
        when (event) {
            is TipScreenEvents.OnFavPressed -> {
                viewModelScope.launch {
                    val thisSet = set.toMutableSet()
                    if (isFavourite.value) thisSet.remove("$categoryId$tipId")
                    else thisSet.add("$categoryId$tipId")
                    isFavourite.value = !isFavourite.value
                    dataStoreRepository.saveFavourites(thisSet)
                    set = thisSet

                }

            }
        }
    }



    val name = mutableStateOf(R.string.app_name)
    val title = mutableStateOf(R.string.app_name)
    val imageId = mutableStateOf(R.drawable.body1)
    val isFavourite = mutableStateOf(false)

    private var categoryId = 1
    private var tipId = 1
    private var set = emptySet<String>()

    init {
        categoryId = savedStateHandle?.get<String>("category_id")?.toInt() ?: 1
        tipId = savedStateHandle?.get<String>("tip_index")?.toInt() ?: 1
        val tip = CategoryConverter.fromIndexToCategory(categoryId).tips[tipId]
        name.value = tip.nameStringRes
        title.value = tip.titleStringRes
        imageId.value = tip.imageRes
        viewModelScope.launch {
            dataStoreRepository.readFavourites().collect{
                set = it
                val string = "$categoryId$tipId"
                set.forEach { item->
                    if (item==string) {
                        isFavourite.value = true
                        return@collect
                    }
                }
            }
        }
    }
}