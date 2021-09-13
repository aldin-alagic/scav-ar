package com.example.scavengerar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scavengerar.data.ActiveLevelItem
import com.example.scavengerar.data.Level
import com.example.scavengerar.data.LevelRepository
import com.example.scavengerar.data.UserLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LevelViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
): ViewModel() {

    val levels: LiveData<List<Level>> = levelRepository.getLevels().asLiveData()

    fun getActiveLevel(userId: Int) = levelRepository.getActiveLevel(userId).asLiveData()
}