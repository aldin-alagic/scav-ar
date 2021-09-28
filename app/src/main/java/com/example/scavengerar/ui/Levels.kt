package com.example.scavengerar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scavengerar.data.Level
import com.example.scavengerar.viewmodels.LevelViewModel

@Composable
fun LevelsScreen(modifier: Modifier, viewModel: LevelViewModel) {
    Column(
        modifier
    ) {
        Levels(viewModel)
    }
}

@Composable
fun Levels(
    viewModel: LevelViewModel
) {
    val levels by viewModel.levels.observeAsState(initial = emptyList())

    if (levels.isEmpty()) {
        Loading()
    } else {
        LevelList(levels)
    }
}

@Composable
fun LevelList(items: List<Level>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    )  {
        items(items) { item ->
            LevelCard(item)
        }
    }
}

@Composable
fun LevelCard(level: Level) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.height(64.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = level.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}