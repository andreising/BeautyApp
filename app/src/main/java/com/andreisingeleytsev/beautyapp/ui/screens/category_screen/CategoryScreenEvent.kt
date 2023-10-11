package com.andreisingeleytsev.beautyapp.ui.screens.category_screen

import com.andreisingeleytsev.beautyapp.ui.utils.Tip

sealed class CategoryScreenEvent{
    data class OnClick(val tip: Tip):CategoryScreenEvent()
}
