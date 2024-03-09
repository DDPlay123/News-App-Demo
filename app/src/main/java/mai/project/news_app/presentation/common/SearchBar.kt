package mai.project.news_app.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mai.project.news_app.R
import mai.project.news_app.presentation.Dimens.IconSize

@Composable
fun SearchBar(
    modifier: Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked = interactionSource.collectIsPressedAsState().value

    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }

    Box(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .searchBarBorder(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(IconSize),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = colorResource(id = R.color.body)
                )
            },
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.input_background),
                unfocusedContainerColor = colorResource(id = R.color.input_background),
                focusedTextColor = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                cursorColor = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSource
        )
    }
}

@Composable
fun Modifier.searchBarBorder(): Modifier {
    return if (!isSystemInDarkTheme()) {
        this then border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        text = "",
        readOnly = false,
        onValueChange = {},
        onSearch = {}
    )
}
