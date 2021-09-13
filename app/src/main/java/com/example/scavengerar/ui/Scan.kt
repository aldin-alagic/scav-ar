package com.example.scavengerar.ui

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scavengerar.ObjectAnalyzer
import com.example.scavengerar.viewmodels.Scan
import com.example.scavengerar.viewmodels.ScanViewModel
import com.example.scavengerar.SimpleCameraPreview
import com.example.scavengerar.viewmodels.ItemViewModel
import com.example.scavengerar.viewmodels.LevelViewModel
import java.lang.Exception

import java.util.*

@Composable
fun ScanScreen(scanViewModel: ScanViewModel, levelViewModel: LevelViewModel) {

    val context = LocalContext.current
    SimpleCameraPreview(ObjectAnalyzer(context) { newScan -> scanViewModel.currentScan = newScan})

    var scan = scanViewModel.currentScan

    val items by scanViewModel.getActiveLevelItems(1).observeAsState(initial = emptyList())
    val activeLevel by levelViewModel.getActiveLevel(1).observeAsState()

    if (items.isNotEmpty() && activeLevel != null && scan != null) {
        try {
            scanViewModel.markItemAsFound(scan.text, items, activeLevel!!.id)
        } catch (e: Exception) {
            Log.e("ERR", e.message ?: "")
        }
    }

    Scan(scan)
}


@Composable
fun Scan(scan: Scan?) {
    Canvas(Modifier.fillMaxSize()) {
        if (scan != null) {
            val offset = Offset(x = scan.frame.top, y = scan.frame.bottom)
            val size = Size(scan.frame.width() * 1.4f, scan.frame.height() * 1.4f)
            drawRect(
                color = Color.Cyan,
                topLeft = offset,
                size = size,
                style = Stroke(6f, pathEffect = PathEffect.cornerPathEffect(15f)),
            )
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(64.dp), verticalArrangement = Arrangement.Bottom
    ) {
        if (scan != null) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                text = scan.text.toUpperCase(Locale.getDefault()),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Cyan
            )
        }
        Row {
            Button( onClick = { /*TODO*/ }, Modifier.weight(0.4f)) {
                Text("Scan")
            }
            Spacer(Modifier.weight(0.1f))
            Button( onClick = { /*TODO*/ }, Modifier.weight(0.4f)) {
                Text("Clear")
            }
        }
    }
}
