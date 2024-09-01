package com.vladzah.pexelapp.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchBarComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    isActiveSearch: Boolean,
    onActiveChange: (Boolean) -> Unit,
    )
{
    var localIsActiveSearch by remember { mutableStateOf(isActiveSearch) }
    val debouncePeriod = 500L
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(query) {
        if (localIsActiveSearch && query.isNotEmpty()) {
            coroutineScope.launch {
                delay(debouncePeriod)
                onQueryChange(query)
            }
        }
    }

    LaunchedEffect(isActiveSearch) {
        localIsActiveSearch = isActiveSearch
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { newQuery ->
                onQueryChange(newQuery)
                if (!localIsActiveSearch) onActiveChange(true)
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(
                    painter = com.vladzah.pexelapp.utils.Icons.SearchIcon,
                    contentDescription = "search icon",
                    tint = Color.Unspecified
                )
            },
            trailingIcon = {
                if (localIsActiveSearch && query.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                            onQueryChange("")
                        }
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                errorContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    PexelAppTheme(dynamicColor = false) {
        SearchBarComponent(
        query = "",
        onQueryChange = {},
            true, {}
        )
    }
}