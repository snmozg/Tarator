package com.sozge.taratornew.components.filters

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.sozge.taratornew.datas.filterCards
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun FilterSection(imageViewModel: ImageViewModel, filterViewModel: FilterViewModel) {
    LazyRow {
        itemsIndexed(filterCards) { index, item ->
            FilterCardItem(index, item, imageViewModel, filterViewModel)
        }
    }
}