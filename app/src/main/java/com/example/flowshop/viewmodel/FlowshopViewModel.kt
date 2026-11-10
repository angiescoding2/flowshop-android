
package com.example.flowshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.flowshop.model.ShoppingList
import com.example.flowshop.model.Member
import com.example.flowshop.model.GroceryItem


class FlowshopViewModel : ViewModel() {

    val lists = mutableStateListOf<ShoppingList>()

    val allMembers = mutableStateListOf(
        Member(id = 0, name = "You", tone = 0, online = true),
        Member(id = 1, name = "Maya Lindgren", tone = 1, online = true),
        Member(id = 2, name = "Alex Chen", tone = 3, online = true),
        Member(id = 3, name = "Sara Park", tone = 4, online = false),
        Member(id = 4, name = "Noah Berg", tone = 2, online = true)
    )

    init {
        lists.add(
            ShoppingList(
                id = "l1",
                name = "Weekly groceries",
                updatedAt = "just now",
                members = listOf(
                    allMembers[0],
                    allMembers[1],
                    allMembers[2]
                ),

                items = listOf(
                    GroceryItem(
                        id = "i1",
                        name = "Sourdough bread",
                        qty = "1 loaf",
                        checked = false,
                        addedBy = 1
                    ),
                    GroceryItem(
                        id = "i2",
                    name = "Whole milk",
                    qty = "2 L",
                    checked = false,
                    addedBy = 0
                ),

                    GroceryItem(
                        id = "i3",
                        name = "Eggs",
                        qty = "1 dozen",
                        checked = false,
                        addedBy = 2
                    ),

                    GroceryItem(
                        id = "i4",
                        name = "Bananas",
                        qty = "6",
                        checked = false,
                        addedBy = 1
                    ),
                    GroceryItem(
                        id = "i5",
                        name = "Greek yoghurt",
                        qty = "500 g",
                        checked = false,
                        addedBy = 0
                    ),
                    GroceryItem(
                        id = "i6",
                        name ="Avocados",
                        qty = "3 ripe",
                        checked = false,
                        addedBy = 2
                    ),
                    GroceryItem(
                        id = "i7",
                        name = "Honey",
                        qty = "",
                        checked = false,
                        addedBy = 1,
                    )
                )

            )
        )
    }
        fun addItem(name: String, qty: String = "") {
            if (name.isBlank() || lists.isEmpty()) return

            val currentList = lists[0]

            val newItem = GroceryItem(
                id = java.util.UUID.randomUUID().toString(),
                name = name.trim(),
                qty = qty,
                checked = false,
                addedBy = 0
            )

            lists[0] = currentList.copy(
                items = currentList.items + newItem
            )
        }

        fun checkItem(itemId: String) {
            if (lists.isEmpty()) return

            val currentList = lists[0]

            lists[0] = currentList.copy(
                items = currentList.items.map { item ->
                    if (item.id == itemId) {
                        item.copy(
                            checked = true,
                            checkedBy = 0
                        )
                    } else {
                        item
                    }
                }
            )
        }
    }




