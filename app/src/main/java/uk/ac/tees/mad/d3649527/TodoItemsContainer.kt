package uk.ac.tees.mad.d3649527

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uk.ac.tees.mad.d3649527.ui.theme.MediumDp

@Composable
fun TodoItemsContainer(
    modifier: Modifier = Modifier,
    todoItemsFlow: Flow<List<TodoItem>> = flowOf(listOf()),
    onItemClick: (TodoItem) -> Unit = {},
    onItemDelete: (TodoItem) -> Unit = {},
    overlappingElementsHeight: Dp = 0.dp
) {
    val todos = todoItemsFlow.collectAsState(initial = listOf()).value
    val isEmpty = remember {
        mutableStateOf(false)
    }
    Header()
    EmptyScreen(isEmpty.value)

    LazyColumn(
        modifier = modifier
            .padding(top = 64.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        todos?.let {
            if(todos.isEmpty())
                isEmpty.value = true
            else isEmpty.value = false

            items(items = todos){
                TodoItemUi(
                    todoItem = it,
                    onItemClick = onItemClick,
                    onItemDelete = onItemDelete
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview
@Composable
fun TodoItemsContainerPreview() {
    TodoItemsContainer(
        todoItemsFlow = flowOf(
            listOf(
                TodoItem(title = "Todo Item 1", isDone = true),
                TodoItem(title = "Todo Item 2"),
                TodoItem(title = "Todo Item 3"),
                TodoItem(title = "Todo Item 4", isDone = true),
            )
        )
    )
}