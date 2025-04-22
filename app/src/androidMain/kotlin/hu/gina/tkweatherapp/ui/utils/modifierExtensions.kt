package hu.gina.tkweatherapp.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope

fun Modifier.disableSplitMotionEvents() =
    pointerInput(Unit) {
        coroutineScope {
            var currentId: Long = -1L
            awaitPointerEventScope {
                while (true) {
                    awaitPointerEvent(PointerEventPass.Initial).changes.forEach { pointerInfo ->
                        when {
                            pointerInfo.pressed && currentId == -1L -> currentId =
                                pointerInfo.id.value

                            pointerInfo.pressed.not() && currentId == pointerInfo.id.value -> currentId =
                                -1

                            pointerInfo.id.value != currentId && currentId != -1L -> pointerInfo.consume()
                            else -> Unit
                        }
                    }
                }
            }
        }
    }

@Composable
fun Modifier.setDefaultStyle(onClick: (() -> Unit)? = null) =
    this.fillMaxWidth()
        .padding(6.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable(enabled = onClick != null) { onClick?.let { it() } }
        .border(
            1.dp,
            MaterialTheme.colorScheme.onPrimaryContainer,
            RoundedCornerShape(16.dp)
        )
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(horizontal = 22.dp, vertical = 16.dp)