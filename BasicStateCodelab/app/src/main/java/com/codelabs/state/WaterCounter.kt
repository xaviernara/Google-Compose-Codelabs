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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function that manages the state of a water counter.
 * It internally uses a `rememberSaveable` state to persist the count value across recompositions
 * and configuration changes (like screen rotation).
 *
 * This function demonstrates state hoisting by managing the state internally and passing down
 * the current count and an increment callback to a stateless child composable.
 *
 * @param modifier The modifier to be applied to the layout.
 *
 * Example Usage:
 *
 * ```
 * StatefulWaterCounter(Modifier.padding(16.dp))
 * ```
 */
@Composable
fun StatefulWaterCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) } // remembers count value across recompositions and configuration changes (portrait and landscape modes)
    StatelessWaterCounter(
        count = count,
        onIncrement = { count++ },
        modifier = modifier
    )
}

/**
 * A stateless composable function that displays a water counter.
 *
 * This composable shows the number of glasses of water consumed and provides a button to increment the count.
 * The button is enabled only when the count is less than 10.
 *
 * @param count The current number of glasses of water consumed.
 * @param onIncrement A lambda function that is called when the "Add one" button is clicked.
 *                    This lambda should be responsible for updating the count in the parent composable.
 * @param modifier The modifier to be applied to the Column layout.
 */
@Composable
fun StatelessWaterCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onIncrement,
            enabled = count < 10,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add one")
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
fun StatefulWaterCounterPreview(){
    StatefulWaterCounter()
}
