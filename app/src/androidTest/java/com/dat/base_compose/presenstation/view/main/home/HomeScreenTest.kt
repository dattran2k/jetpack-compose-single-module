package com.dat.base_compose.presenstation.view.main.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import com.dat.base_compose.core.common.TestTag
import com.dat.base_compose.data.model.TodoItem
import com.dat.base_compose.presenstation.theme.BaseJetpackComposeTheme
import org.junit.Rule
import org.junit.Test


@ExperimentalTestApi
@ExperimentalMaterialApi
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun if_list_to_do_not_empty_should_show() {
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState(getListTodoItem())
                )
            }
        }
        composeTestRule.onAllNodes(hasText("Fake Title", ignoreCase = true)).assertCountEquals(4)
    }

    @Test
    fun if_list_to_do_not_empty_should_show_and_click_able() {
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState(getListTodoItem())
                )
            }
        }
        composeTestRule.onNode(hasText("Fake Title", ignoreCase = true) and hasText("1")).assertHasClickAction()
    }

    @Test
    fun if_list_to_do_not_empty_should_show_and_show_loading_when_scroll_to_end() {
        val listTodo = getListTodoItemVeryMuchItem()
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState(
                        listTodo,
                        isLoading = true,
                        isEndReached = false,

                    ),
                    currentPage = 2
                )
            }
        }
        // scroll to bottom
        composeTestRule.onNodeWithTag(TestTag.LAZY_COLUMN_TAG).performScrollToIndex(listTodo.lastIndex)

        // check progress
        composeTestRule.onNodeWithTag(TestTag.CIRCULAR_PROGRESS_INDICATOR_TAG_2).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.CIRCULAR_PROGRESS_INDICATOR_TAG).assertDoesNotExist()

    }

    @Test
    fun if_list_to_do_not_empty_and_show_loading() {
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState(getListTodoItem())
                )
            }
        }
        composeTestRule.onAllNodes(hasText("Fake Title", ignoreCase = true)).assertCountEquals(4)
        composeTestRule.onNode(hasText("Fake Title", ignoreCase = true) and hasText("1")).assertHasClickAction()
    }

    @Test
    fun if_list_to_do_empty_should_not_show() {
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState()
                )
            }
        }
        composeTestRule.onAllNodes(hasText("Fake Title", ignoreCase = true)).assertCountEquals(0)
    }

    @Test
    fun if_is_loading_and_current_page_is_1_should_show_loading_center() {
        // Start the app
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState(
                        isLoading = true
                    ),
                    currentPage = 1
                )
            }
        }
        // Verify that the CircularProgressIndicator is displayed
        composeTestRule.onNodeWithTag(TestTag.CIRCULAR_PROGRESS_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun if_is_loading_and_current_page_is_not_1_should_not_show_loading_center() {
        // Start the app
        composeTestRule.setContent {
            BaseJetpackComposeTheme {
                HomeScreen(
                    HomeUIState(
                        isLoading = true
                    ),
                    currentPage = 2
                )
            }
        }
        // Verify that the CircularProgressIndicator is displayed
        composeTestRule.onNodeWithTag(TestTag.CIRCULAR_PROGRESS_INDICATOR_TAG).assertDoesNotExist()
    }

}

fun getListTodoItem() = listOf(
    TodoItem(false, 1, "Fake title", 10),
    TodoItem(false, 2, "Fake title", 10),
    TodoItem(false, 3, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
)

fun getListTodoItemVeryMuchItem() = listOf(
    TodoItem(false, 1, "Fake title", 10),
    TodoItem(false, 2, "Fake title", 10),
    TodoItem(false, 3, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
    TodoItem(false, 4, "Fake title", 10),
)