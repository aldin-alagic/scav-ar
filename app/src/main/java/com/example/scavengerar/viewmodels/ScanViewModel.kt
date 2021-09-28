package com.example.scavengerar.viewmodels

import android.graphics.RectF
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.scavengerar.data.ActiveLevelItem
import com.example.scavengerar.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel() {

    var currentScan: Scan? by mutableStateOf(null)

    fun getActiveLevelItems(userId: Int) = itemRepository.getActiveLevelItems(userId).asLiveData()

    fun markItemAsFound(itemName: String, activeItems: List<ActiveLevelItem>, activeLevelId: Int) {

        viewModelScope.launch {
            val item = activeItems.firstOrNull { it.name == itemName.uppercase(Locale.getDefault()) }
            if (item != null) {
                itemRepository.markItemAsFound(item.id, activeLevelId)
            }
        }
    }
}

data class Scan (var  color: Color = Color.Cyan, var frame: RectF, var text: String)