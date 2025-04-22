package hu.gina.tkweatherapp.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun SunPath(
    modifier: Modifier = Modifier,
    progress: Float
) {

    val sunColor = MaterialTheme.colorScheme.onPrimaryContainer
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        val path = Path()
        val steps = 100

        // Cosinus
        for (i in 0..steps) {
            val t = i / steps.toFloat()
            val x = t * width
            val y = height * 0.5f * (1 + kotlin.math.cos(2 * Math.PI * t)).toFloat()

            if (i == 0) path.moveTo(x, y)
            else path.lineTo(x, y)
        }

        // Cosinus line
        drawPath(
            path = path,
            color = Color.Gray,
            style = Stroke(width = 1.dp.toPx(), cap = StrokeCap.Round)
        )

        // Horizontal divider line
        val horizonY = height / 2f
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, horizonY),
            end = Offset(width, horizonY),
            strokeWidth = 1.dp.toPx()
        )

        // Position of the sun
        if (progress in 0f..1f) {
            val sunX = progress * width
            val sunY = height * 0.5f * (1 + kotlin.math.cos(2 * Math.PI * progress)).toFloat()

            drawCircle(
                color = sunColor,
                radius = 10.dp.toPx(),
                center = Offset(sunX, sunY)
            )
        }
    }
}



