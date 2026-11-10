package com.example.flowshop

import com.example.flowshop.ui.theme.FlowshopColors
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowshop.viewmodel.FlowshopViewModel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowshop.ui.theme.FlowshopTheme

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.flowshop.ui.theme.FlowshopRadius

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowshopTheme {

                val flowshopViewModel: FlowshopViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = FlowshopColors.Champagne
                ) { innerPadding ->
                    var newItemName by remember { mutableStateOf("") }

                    val shoppingList = flowshopViewModel.lists.firstOrNull()
                    val activeItems = shoppingList?.items?.filter {
                        !it.checked && !it.removed
                    }.orEmpty()

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 20.dp)
                            .imePadding(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(FlowshopRadius.Card),
                            colors = CardDefaults.cardColors(
                                containerColor = FlowshopColors.AntiqueGold
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = shoppingList?.name ?: "Shopping list",
                                    color = FlowshopColors.Espresso,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Text(
                                    text = "${activeItems.size} items remaining",
                                    color = FlowshopColors.Espresso
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = newItemName,
                                onValueChange = { newItemName = it },
                                label = { Text("Add an item") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    flowshopViewModel.addItem(newItemName)
                                    newItemName = ""
                                },
                                enabled = newItemName.isNotBlank(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FlowshopColors.Burgundy,
                                    contentColor = FlowshopColors.Champagne
                                )
                            ) {
                                Text("Add")
                            }
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(
                                items = activeItems,
                                key = { it.id }
                            ) { item ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(FlowshopRadius.Row),
                                    colors = CardDefaults.cardColors(
                                        containerColor = FlowshopColors.AntiqueGold
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = item.checked,
                                            onCheckedChange = { checked ->
                                                if (checked) {
                                                    flowshopViewModel.checkItem(item.id)
                                                }
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = FlowshopColors.Burgundy,
                                                uncheckedColor = FlowshopColors.Espresso
                                            )
                                        )

                                        Column {
                                            Text(
                                                text = item.name,
                                                color = FlowshopColors.Espresso,
                                                fontWeight = FontWeight.SemiBold
                                            )

                                            if (item.qty.isNotBlank()) {
                                                Text(
                                                    text = item.qty,
                                                    color = FlowshopColors.Espresso,
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    }

                }
            }
        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlowshopTheme {
        Greeting("Android")
    }
}