package com.vladzah.pexelapp.ui.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
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
import com.vladzah.pexelapp.utils.Icons.SearchIcon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    var isActiveSearch by remember { mutableStateOf(false) }
    val debouncePeriod = 500L
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(query) {
        if (isActiveSearch && query.isNotEmpty()) {
            coroutineScope.launch {
                delay(debouncePeriod)
                onQueryChange(query)
            }
        }
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.07f)
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(100.dp)),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {
            isActiveSearch = false
            onQueryChange(query)
        },
        active = isActiveSearch,
        onActiveChange = { isActiveSearch = true },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                painter = SearchIcon,
                contentDescription = "search icon",
                tint = Color.Unspecified
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            dividerColor = Color.LightGray,
            inputFieldColors = TextFieldDefaults.colors(
                cursorColor = Color.Black
            ),
        ),
        trailingIcon = {
            if (isActiveSearch) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    modifier = Modifier.clickable {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                        } else {
                            isActiveSearch = false
                        }
                    }
                )
            }
        }
    ) {
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    PexelAppTheme(dynamicColor = false) {
        SearchBarComponent(
            "", {}
        )
    }
}