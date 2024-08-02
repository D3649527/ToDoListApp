package uk.ac.tees.mad.d3649527

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.d3649527.MainViewModel
import uk.ac.tees.mad.d3649527.TodoInputBar
import uk.ac.tees.mad.d3649527.TodoItemsContainer
import uk.ac.tees.mad.d3649527.ui.theme.OverlappingHeight
import uk.ac.tees.mad.d3649527.ui.theme.gradient

@Composable
fun Home(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize().background(gradient),
    ) {
        TodoItemsContainer(
            todoItemsFlow = mainViewModel.todos,
            onItemClick = mainViewModel::toggleTodo,
            onItemDelete = mainViewModel::removeTodo,
            overlappingElementsHeight = OverlappingHeight
        )
        TodoInputBar(
            modifier = Modifier.align(Alignment.BottomStart),
            onAddButtonClick = mainViewModel::addTodo
        )
    }
}