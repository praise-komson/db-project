package ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import kotlinx.coroutines.*
import ui.theme.SkyBase
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun imagePainter(url: String): Painter {
    return produceState<Painter>(ColorPainter(SkyBase)) {
        value = BitmapPainter(loadImageBitmapFromUrl(url))
    }.value
}

private val scope = MainScope() + CoroutineName("ImageCache")
private val imageCache = mutableMapOf<String, Deferred<ImageBitmap>>()

private suspend fun loadImageBitmapFromUrl(url: String): ImageBitmap {
    return imageCache.getOrPut(url) {
        scope.async(Dispatchers.IO) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connect()

            loadImageBitmap(connection.inputStream)
        }
    }.await()
}
