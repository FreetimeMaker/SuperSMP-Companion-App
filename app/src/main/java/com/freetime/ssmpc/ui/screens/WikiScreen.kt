package com.freetime.ssmpc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class WikiEntry(val title: String, val content: String)

@Composable
fun WikiScreen() {
    val entries = listOf(
        WikiEntry("How to Claim Land?", "Use a golden shovel to select two corners of your area. Or use /claim to claim the current chunk."),
        WikiEntry("How to set Home?", "Use /sethome <name> to save your current location. You can teleport back using /home <name>."),
        WikiEntry("Server Rules", "1. No Griefing.\n2. No Cheating/X-Ray.\n3. Be respectful in chat.\n4. No lag machines."),
        WikiEntry("Economy Basics", "Earn money by selling items in /shop or trading with players. Use /bal to check your balance.")
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(entries) { entry ->
            WikiExpandableCard(entry)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun WikiExpandableCard(entry: WikiEntry) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = entry.title, style = MaterialTheme.typography.titleMedium)
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = entry.content, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
