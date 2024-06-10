package br.com.kassiano.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun <T> UiStateComponent(
    stateValue: Result<T>?,
    onSuccess: @Composable (T?) -> Unit,
    onLoading: @Composable () -> Unit,
    onError: @Composable (Throwable?) -> Unit
) {
    if (stateValue?.isSuccess == true) {
        onSuccess(stateValue.getOrNull())
    } else if (stateValue?.isFailure == true) {
        onError(stateValue.exceptionOrNull())
    } else {
        onLoading()
    }
}

@Composable
fun CenterComponent(
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxHeight()
        .wrapContentSize(Alignment.Center)
    ) {
        content()
    }
}

@Composable
fun ErrorComponent() {
    CenterComponent {
        Text(
            text = "Erro ao carregar informações, tente novamente.",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun LoadingComponent() {
    CenterComponent {
        CircularProgressIndicator()
    }
}