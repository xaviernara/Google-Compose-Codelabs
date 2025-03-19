/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codelabs.state

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays a list of wellness tasks.
 *
 * This function uses a `LazyColumn` to efficiently display a potentially large list of
 * `WellnessTask` items. Each task is rendered using the `WellnessTaskItem` composable.
 *
 * @param list The list of `WellnessTask` objects to display.
 * @param onCheckedTask Callback function invoked when the checked state of a task changes.
 *                      It provides the `WellnessTask` and the new checked state (Boolean).
 * @param onCloseTask Callback function invoked when the close button of a task is clicked.
 *                    It provides the `WellnessTask` that was closed.
 * @param modifier Modifier to be applied to the `LazyColumn`.
 */
@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            /**
             * Use key param to define unique keys representing the items in a mutable list,
             * instead of using the default key (list position). This prevents unnecessary
             * recompositions.
             */
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}



@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
fun WellnessTasksListPreview(){
    val wellnessTaskList = List(30) { task -> WellnessTask(task, "Task # $task") }
    WellnessTasksList(
        list = wellnessTaskList,
        onCheckedTask = ({task, checked -> !checked}),
        onCloseTask = ({task -> task.id}),
        modifier = Modifier

    )
}
