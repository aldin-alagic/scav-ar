package com.example.scavengerar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scavengerar.data.Item
import com.example.scavengerar.data.ItemRepository
import com.example.scavengerar.data.Level
import com.example.scavengerar.data.LevelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
    private val itemRepository: ItemRepository,
): ViewModel() {

    val levels: LiveData<List<Level>> = levelRepository.getLevels().asLiveData()
    val items: LiveData<List<Item>> = itemRepository.getItems().asLiveData()
}