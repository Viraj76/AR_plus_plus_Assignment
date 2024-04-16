package com.example.newsapp.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.presentation.Dimens.SmallButtonCornerRadius


//@Preview
//@Composable
//fun Prev() {
//    NewsButton(text = "Get Started") {
//    }
//}
@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(SmallButtonCornerRadius)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            , color = Color.LightGray
            )
    }
}