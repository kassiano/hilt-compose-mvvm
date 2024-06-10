package br.com.kassiano.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ListTile(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    title: String,
    subtitle: String? = null,
    trailingIcon: ImageVector? = null,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .clickable(enabled = onClick != null, onClick = { onClick?.invoke() })
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge)
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
