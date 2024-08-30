package com.vladzah.pexelapp.models

data class TopicUiModel (
    val label: String,
    val isSelected: Boolean
)

internal fun String.toTopicUiModel(): TopicUiModel {
    return TopicUiModel(
        label = this,
        isSelected = false
    )
}