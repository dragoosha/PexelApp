package com.vladzah.pexelapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladzah.pexelapp.events.ChatScreenEvents
import com.vladzah.pexelapp.models.MessageUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor()
: ViewModel() {
    private val _messages = MutableStateFlow<List<MessageUi>>(emptyList())
    val messages: StateFlow<List<MessageUi>> = _messages

    private var messageIdCounter = 0


    private fun generateIncomingMessages() {
        viewModelScope.launch {
            while (true) {
                delay(5000)
                if (messageIdCounter > 0) {
                    if (!messages.value[messageIdCounter - 1].isOutgoing) {
                        updateMessage(messageIdCounter - 1)
                    }
                }
                addMessage(
                    MessageUi(
                        id = ++messageIdCounter,
                        content = "Incoming message ${messageIdCounter}",
                        timestamp = System.currentTimeMillis(),
                        isOutgoing = false,
                        isClosingType = true
                    )
                )
            }
        }
    }
    private fun sendMessage(content: String) {
        if (messageIdCounter > 0) {
            if (messages.value[messageIdCounter - 1].isOutgoing) {
                updateMessage(messageIdCounter - 1)
            }
        }
        addMessage(
            MessageUi(
                id = ++messageIdCounter,
                content = content,
                timestamp = System.currentTimeMillis(),
                isOutgoing = true,
                isClosingType = true
            )
        )
    }

    private fun updateMessage(id: Int) {
        _messages.value[id].isClosingType = false
    }

    private fun addMessage(message: MessageUi) {
        _messages.value = _messages.value + message
    }

    fun onEvent(event: ChatScreenEvents) {
        when(event) {
            is ChatScreenEvents.onButtonSendEvent -> {
                sendMessage(event.content)
                generateIncomingMessages()
            }
        }
    }
}