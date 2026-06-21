package com.freetime.ssmpc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShopItem(val name: String, val buyPrice: Double, val sellPrice: Double)

@Composable
fun PriceCalculatorScreen() {
    val items = listOf(
        ShopItem("Diamond", 500.0, 100.0),
        ShopItem("Netherite Ingot", 5000.0, 1000.0),
        ShopItem("Gold Ingot", 100.0, 20.0),
        ShopItem("Iron Ingot", 50.0, 10.0),
        ShopItem("Emerald", 200.0, 40.0)
    )

    var selectedItem by remember { mutableStateOf(items[0]) }
    var quantity by remember { mutableStateOf("1") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Price Calculator", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Basic calculator
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Calculate Value", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                
                // Simple selection and quantity
                TextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                val q = quantity.toDoubleOrNull() ?: 0.0
                Text(text = "Total Sell Value: ${q * selectedItem.sellPrice}")
                Text(text = "Total Buy Cost: ${q * selectedItem.buyPrice}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Item Price List", style = MaterialTheme.typography.titleLarge)
        LazyColumn {
            items(items) { item ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = item.name)
                    Column {
                        Text(text = "Buy: ${item.buyPrice}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Sell: ${item.sellPrice}", style = MaterialTheme.typography.bodySmall)
                    }
                    Button(onClick = { selectedItem = item }) {
                        Text("Select")
                    }
                }
                HorizontalDivider()
            }
        }
    }
}
