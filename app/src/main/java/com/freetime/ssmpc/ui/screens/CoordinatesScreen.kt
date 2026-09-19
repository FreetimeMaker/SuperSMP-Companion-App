package com.freetime.ssmpc.ui.screens

import com.freetime.ssmpc.ui.glass.superSMPGlass
import com.freetime.ssmpc.ui.glass.SuperSMPGlassButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freetime.ssmpc.data.coords.CoordinateEntity
import com.freetime.ssmpc.ui.viewmodels.CoordinatesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordinatesScreen(viewModel: CoordinatesViewModel = viewModel()) {
    val coordinates by viewModel.allCoordinates.collectAsState(initial = emptyList())
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Coordinate")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(coordinates) { coord ->
                CoordinateItem(coord, onDelete = { viewModel.deleteCoordinate(coord) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    if (showAddDialog) {
        AddCoordinateDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { name, x, y, z, dim, desc ->
                viewModel.addCoordinate(name, x, y, z, dim, desc)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun CoordinateItem(coordinate: CoordinateEntity, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().superSMPGlass(RoundedCornerShape(24.dp))) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = coordinate.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "X: ${coordinate.x} Y: ${coordinate.y} Z: ${coordinate.z}", style = MaterialTheme.typography.bodyMedium)
                Text(text = coordinate.dimension, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun AddCoordinateDialog(onDismiss: () -> Unit, onAdd: (String, Double, Double, Double, String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var x by remember { mutableStateOf("") }
    var y by remember { mutableStateOf("") }
    var z by remember { mutableStateOf("") }
    var dim by remember { mutableStateOf("Overworld") }
    var desc by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .superSMPGlass(RoundedCornerShape(28.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Add Coordinate", style = MaterialTheme.typography.titleLarge)
            TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            TextField(value = x, onValueChange = { x = it }, label = { Text("X") })
            TextField(value = y, onValueChange = { y = it }, label = { Text("Y") })
            TextField(value = z, onValueChange = { z = it }, label = { Text("Z") })
            TextField(value = dim, onValueChange = { dim = it }, label = { Text("Dimension") })
            TextField(value = desc, onValueChange = { desc = it }, label = { Text("Description") })
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = onDismiss) { Text("Cancel") }
                SuperSMPGlassButton(onClick = {
                    onAdd(
                        name,
                        x.toDoubleOrNull() ?: 0.0,
                        y.toDoubleOrNull() ?: 0.0,
                        z.toDoubleOrNull() ?: 0.0,
                        dim,
                        desc
                    )
                }) { Text("Add") }
            }
        }
    }
}
