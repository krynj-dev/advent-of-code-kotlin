package au.com.krynj.aoc.cmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import au.com.krynj.aoc.framework.AoCDay

@Composable
@Preview
fun AoCComposeAppLanding(dayClasses: Set<AoCDay<*, *>>) {
    val sortedDays = dayClasses.toList().sortedBy { it.getDay() }
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        LazyVerticalGrid(
            columns = GridCells.Fixed(5), // Set the number of columns
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sortedDays) {
                GridItem(it)
            }
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(showContent) {
                val greeting = remember { "HI" }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
fun GridItem(item: AoCDay<*, *>) {
    var showContent by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp),
        elevation = 4.dp
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("${item.getDay()}", fontWeight = FontWeight.Bold)
        }
    }
}