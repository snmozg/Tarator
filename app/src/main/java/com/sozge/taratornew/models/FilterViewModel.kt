package com.sozge.taratornew.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.ViewModel

class FilterViewModel: ViewModel(){
    private val _filter = mutableStateOf<ColorFilter?>(null)
    val filter : State<ColorFilter?> = _filter

    fun updateFilter(newFilter: ColorFilter){
        _filter.value = newFilter
    }
    fun deleteFilter(){
        _filter.value = null
    }
}