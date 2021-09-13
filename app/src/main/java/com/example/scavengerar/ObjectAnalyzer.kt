package com.example.scavengerar

import android.content.Context
import android.graphics.*
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.scavengerar.viewmodels.Scan
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import java.io.ByteArrayOutputStream

class ObjectAnalyzer(private val context: Context, private val onScanChange: (Scan?) -> Unit) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.convertImageProxyToBitmap()?.let { bitmap ->
            val scan = runObjectDetection(context, bitmap)
            onScanChange.invoke(scan)
        }
        imageProxy.close()
    }
}

//based on the code from https://stackoverflow.com/a/56812799
fun ImageProxy.convertImageProxyToBitmap(): Bitmap? {

    val yBuffer = planes[0].buffer // Y
    val vuBuffer = planes[2].buffer // VU

    val ySize = yBuffer.remaining()
    val vuSize = vuBuffer.remaining()

    val nv21 = ByteArray(ySize + vuSize)

    yBuffer.get(nv21, 0, ySize)
    vuBuffer.get(nv21, ySize, vuSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 50, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

/**
 *  TFLite Object Detection function
 */
private fun runObjectDetection(context: Context, bitmap: Bitmap): Scan? {

    // Step 1: Create TFLite's TensorImage object
    val image = TensorImage.fromBitmap(bitmap)

    // Step 2: Initialize the detector object
    val options = ObjectDetector.ObjectDetectorOptions.builder()
        .setMaxResults(5)
        .setScoreThreshold(0.4f)
        .build()
    val detector = ObjectDetector.createFromFileAndOptions(
        context,
        "model.tflite",
        options
    )

    // Step 3: Feed given image to the detector
    val results = detector.detect(image)
    if (results.isNullOrEmpty()) return null
    val bestResult = results.maxByOrNull { it ->  it.categories.first().score } ?: return null

    // Step 4: Parse the detection result and show it
    val category = bestResult.categories.first()
    val text = category.label
    val resultToDisplay = DetectionResult(bestResult.boundingBox, text)

    // Step 5: Draw the detection result on the bitmap and show it.
    return Scan(frame = resultToDisplay.boundingBox, text = text)
}

/**
 *  A class to store the visualization info of a detected object.
 */
data class DetectionResult(val boundingBox: RectF, val text: String)
