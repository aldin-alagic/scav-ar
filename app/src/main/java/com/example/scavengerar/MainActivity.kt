package com.example.scavengerar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.scavengerar.data.Item
import com.example.scavengerar.utilities.S3_URL
import com.example.scavengerar.viewmodels.MainViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.Color
import com.example.scavengerar.ui.theme.blue800
import org.intellij.lang.annotations.JdkConstants


/**
 * Main activity of the application.
 *
 * Container for the Buttons & Logs fragments. This activity simply tracks clicks on buttons.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemsScreen ()
        }
    }

    @Composable
    fun ItemsScreen() {
        val items by viewModel.items.observeAsState(initial = emptyList())

        if (items.isEmpty()) {
            Loading()
        } else {
            ItemList(items)
        }
    }

    @Composable
    fun Loading() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
        }
    }

    @Composable
    fun ItemList(items: List<Item>) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )  {
            items(items) { item ->
                ItemCard(item)
            }
        }
    }

    @Composable
    fun ItemCard(item: Item) {
        val image : String = S3_URL + item.imageUrl
        Card (
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp),
        ) {
            Row (
            ) {
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
                        style = MaterialTheme.typography.h5,
                    )
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = blue800,
                        modifier = Modifier.size(48.dp).padding(8.dp)
                    )
                }
            }
        }
    }
}