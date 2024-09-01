package com.vladzah.pexelapp.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladzah.pexelapp.events.ChatScreenEvents
import com.vladzah.pexelapp.models.MessageUi
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.viewmodels.ChatViewModel

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel()
) {

    val messages = viewModel.messages.collectAsState()

    ChatScreenLayout(
        messages = messages,
        onButtonSend = { message ->
            viewModel.onEvent(ChatScreenEvents.onButtonSendEvent(message))
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenLayout(
    messages: State<List<MessageUi>>,
    onButtonSend: (String) -> Unit
) {

    val state = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        LazyColumn(
            reverseLayout = true,
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            items(messages.value) { message ->
                MessageBubble(message)

            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val messageText = remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.secondary)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)

        ) {
            TextField(
                value = messageText.value,
                onValueChange = { messageText.value = it },
                placeholder = { Text("Enter message") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.background,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30.dp)
                )

            Button(onClick = {
                onButtonSend(messageText.value)
                messageText.value = ""
            }) {
                Text("Send")
            }

        }
    }
}

@Composable
fun MessageBubble(message: MessageUi) {

    val backgroundColor = if (message.isOutgoing) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val alignment = if (message.isOutgoing) Alignment.BottomEnd else Alignment.BottomStart

    val modifier: Modifier = if (message.isClosingType) Modifier.drawBehind {
        val cornerRadius = 8.dp.toPx()
        val triangleHeight = 5.dp.toPx()
        val triangleWidth = 20.dp.toPx()
        val padding = 8.dp.toPx()
        val trianglePath = Path().apply {
            if(message.isOutgoing) {
                moveTo(size.width - padding , size.height - cornerRadius - padding)
                lineTo(size.width, size.height)
                lineTo(size.width - triangleWidth - padding, size.height - cornerRadius - padding)
                close()
            } else {
                moveTo(padding, size.height - cornerRadius - padding)
                lineTo(0f, size.height)
                lineTo(triangleWidth + padding, size.height - cornerRadius - padding)
                close()
            }
        }
        drawPath(
            path = trianglePath,
            color = backgroundColor
        )
    } else Modifier

    Box(
        contentAlignment = alignment,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = message.content,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    PexelAppTheme {
        val message = MessageUi (
            id = 1,
            content = "Hello",
            timestamp = 0L,
            isOutgoing = false,
            isClosingType = true
        )
        MessageBubble(message)
    }
}