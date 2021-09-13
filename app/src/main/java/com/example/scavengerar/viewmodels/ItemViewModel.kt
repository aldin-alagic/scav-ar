package com.example.scavengerar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.scavengerar.data.ActiveLevelItem
import com.example.scavengerar.data.Item
import com.example.scavengerar.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
): ViewModel() {

    val items: LiveData<List<Item>> = itemRepository.getItems().asLiveData()

    fun getLevelItems(userId: Int) = itemRepository.getLevelItems(userId).asLiveData()
}