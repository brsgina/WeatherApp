package hu.gina.tkweatherapp.ui.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataRow(title: String, value: String?, unit: String?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(title)
        Spacer(modifier = Modifier.weight(1f))
        Text((value?.let { it + (unit?.let { u -> " $u" } ?: "") } ?: "NA"))
    }
}

@Composable
fun BigText(text: String, verticalPadding: Dp? = null) {
    Text(
        text = text,
        style = TextStyle(fontSize = 26.sp),
        modifier = Modifier.padding(vertical = verticalPadding ?: 0.dp)
    )
}

@Composable
fun MediumText(text: String) {
    Text(text = text, style = TextStyle(fontSize = 22.sp))
}

@Composable
fun SmallText(text: String, isBold: Boolean = false) {
    Text(
        text = text, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    )
}

@Composable
fun TinyText(text: String, isBold: Boolean = false) {
    Text(
        text = text, style = TextStyle(
            fontSize = 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    )
}