package com.example.scavengerar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.scavengerar.R
import com.example.scavengerar.data.ActiveLevelItem
import com.example.scavengerar.utilities.S3_URL
import com.example.scavengerar.viewmodels.ItemViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemsScreen(modifier: Modifier, viewModel: ItemViewModel) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.start_scaning),
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        Items(viewModel)
    }
}

@Composable
fun Items(
    viewModel: ItemViewModel
) {
    val items by viewModel.getLevelItems(1).observeAsState(initial = emptyList())

    if (items.isEmpty()) {
        Loading()
    } else {
        ItemList(items)
    }
}

@Composable
fun ItemList(items: List<ActiveLevelItem>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    )  {
        items(items) { item ->
            ItemCard(item)
        }
    }
}

@Composable
fun ItemCard(item: ActiveLevelItem) {
    val image : String = S3_URL + item.imageUrl
    Card (
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row {
            GlideImage(
                imageModel = image,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp).padding(8.dp)
            )
            Row(
                modifier = Modifier.height(64.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondary
                )
                if (item.completed) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(48.dp).padding(8.dp)
                    )
                }
            }
        }
    }
}