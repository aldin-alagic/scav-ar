package com.example.scavengerar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scavengerar.data.ActiveLevelItem
import com.example.scavengerar.data.Item
import com.example.scavengerar.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel() {

    val items: LiveData<List<Item>> = itemRepository.getItems().asLiveData()

    fun getActiveLevel(userId: Int) : LiveData<List<ActiveLevelItem>> {
        return itemRepository.getActiveLevelItems(userId).asLiveData()
    }
}