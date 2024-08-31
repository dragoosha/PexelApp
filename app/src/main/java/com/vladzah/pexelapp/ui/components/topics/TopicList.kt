package com.vladzah.pexelapp.ui.components.topics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vladzah.pexelapp.models.TopicUiModel
import com.vladzah.pexelapp.ui.theme.PexelAppTheme


@Composable
fun TopicList(
    list: List<TopicUiModel>,
    query: String,
    onClick: (TopicUiModel) -> Unit
) {
    val updatedList = list.map { topic ->
        topic.copy(isSelected = topic.label.equals(query, ignoreCase = true))
    }

    LazyRow(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        items(updatedList.size) { index ->
            Topic(
                topic = updatedList[index],
                onClick = { selectedTopic ->
                    selectedTopic.isSelected != selectedTopic.isSelected
                    onClick(selectedTopic)
                }
            )

            if (index != updatedList.lastIndex) {
                Spacer(Modifier.width(12.dp))
            }
        }
    }
}

@Composable
fun Topic(
    topic: TopicUiModel,
    onClick: (TopicUiModel) -> Unit
) {
    val boxColor: Color
    val textColor: Color

    if (topic.isSelected) {
        boxColor = MaterialTheme.colorScheme.primaryContainer
        textColor = MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        boxColor = MaterialTheme.colorScheme.secondaryContainer
        textColor = MaterialTheme.colorScheme.onSecondaryContainer
    }

    Text(
        text = topic.label,
        color = textColor,
        modifier = Modifier
            .background(
                color = boxColor,
                shape = RoundedCornerShape(100)
            )
            .clip(RoundedCornerShape(100))
            .clickable { onClick(topic) }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewTopic() {
    PexelAppTheme(dynamicColor = false) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp),
        ) {
            Topic(topic = TopicUiModel("Ice", true)) {

            }
            Spacer(modifier = Modifier.width(10.dp))
            Topic(topic = TopicUiModel("Ice", false)) {

            }

        }
    }
}