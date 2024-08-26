package com.vladzah.pexelapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.material3.ColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    query: String,
    onQueryChange: (String) -> Unit
) {
    var isActiveSearch by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { isActiveSearch = false },
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
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
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