package uk.ac.tees.mad.d3649527

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.d3649527.ui.theme.LargeDp
import uk.ac.tees.mad.d3649527.ui.theme.MediumDp
import uk.ac.tees.mad.d3649527.ui.theme.TODO_ITEM_UI_CONTAINER_CHECKED_CONTENT_DESCRIPTION
import uk.ac.tees.mad.d3649527.ui.theme.TODO_ITEM_UI_CONTAINER_TEST_TAG
import uk.ac.tees.mad.d3649527.ui.theme.TODO_ITEM_UI_CONTAINER_UNCHECKED_CONTENT_DESCRIPTION
import uk.ac.tees.mad.d3649527.ui.theme.TODO_ITEM_UI_DELETE_BUTTON_TEST_TAG
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemActionButtonRippleRadius
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemBackgroundColor
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemHeight
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemIconColor
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemIconSize
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemTextColor
import uk.ac.tees.mad.d3649527.ui.theme.TodoItemTitleTextStyle
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TodoItemUi(
    todoItem: TodoItem = TodoItem(title = "Todo Item"),
    onItemClick: (TodoItem) -> Unit = {},
    onItemDelete: (TodoItem) -> Unit = {}
) {
    val backgroundColor = if (todoItem.isDone) TodoItemBackgroundColor.copy(alpha = 0.5f) else TodoItemBackgroundColor
    val textColor = if (todoItem.isDone) TodoItemTextColor.copy(alpha = 0.5f) else TodoItemTextColor
    val textDecoration = if (todoItem.isDone) TextDecoration.LineThrough else null

    val iconId = if (todoItem.isDone) R.drawable.ic_selected_check_box else R.drawable.ic_empty_check_box
    val iconColorFilter = if (todoItem.isDone) ColorFilter.tint(TodoItemIconColor.copy(alpha = 0.5f)) else ColorFilter.tint(TodoItemIconColor)
    val iconTintColor = if (todoItem.isDone) TodoItemIconColor.copy(alpha = 0.5f) else TodoItemIconColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(TodoItemHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        Box(contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(start = 6.dp, top = 1.dp)){
            Text(text = SimpleDateFormat("dd MMM yyyy").format(Date()), fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp)
        }
        Row(
            modifier = Modifier
                .testTag(TODO_ITEM_UI_CONTAINER_TEST_TAG)
                .semantics {
                    contentDescription =
                        if (todoItem.isDone) TODO_ITEM_UI_CONTAINER_CHECKED_CONTENT_DESCRIPTION
                        else TODO_ITEM_UI_CONTAINER_UNCHECKED_CONTENT_DESCRIPTION
                }
                .fillMaxSize()
                .background(backgroundColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { onItemClick(todoItem) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .padding(MediumDp)
                    .size(TodoItemIconSize),
                colorFilter = iconColorFilter
            )
            Text(
                text = todoItem.title,
                modifier = Modifier.weight(1f),
                style = TodoItemTitleTextStyle.copy(color = textColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = textDecoration
            )
            IconButton(
                onClick = { onItemDelete(todoItem) },
                modifier = Modifier
                    .size(TodoItemActionButtonRippleRadius)
                    .testTag(TODO_ITEM_UI_DELETE_BUTTON_TEST_TAG)
            ) {
                Icon(
                    modifier = Modifier.size(TodoItemIconSize),
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = iconTintColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview
@Composable
fun TodoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        TodoItemUi(todoItem = TodoItem(title = "Wash dishes"))
        TodoItemUi(todoItem = TodoItem(title = "Do laundry", isDone = true))
        TodoItemUi(todoItem = TodoItem(title = "Clean room"))
        TodoItemUi(todoItem = TodoItem(title = "Buy groceries", isDone = true))
    }
}