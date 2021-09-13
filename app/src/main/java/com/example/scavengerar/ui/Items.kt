/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.scavengerar.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.CameraEnhance
import androidx.compose.material.icons.rounded.Cameraswitch
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scavengerar.R
import com.example.scavengerar.data.ActiveLevelItem
import com.example.scavengerar.data.Item
import com.example.scavengerar.ui.theme.BlueTheme
import com.example.scavengerar.ui.theme.ScavArTheme
import com.example.scavengerar.utilities.S3_URL
import com.example.scavengerar.viewmodels.ItemViewModel
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
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
fun Loading() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
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