package com.example.flowshop.model


import java.util.UUID

data class Member(
    val id: Int,
    var name: String,
    var tone: Int,
    var online: Boolean
) {
    val initials: String
        get() {
            val parts = name.split(" ")
                .filter { it.isNotBlank() }

            return if (parts.size >= 2) {
                "${parts[0].first()}${parts[1].first()}"
                    .uppercase()
            } else {
                name.take(2).uppercase()
            }
        }

    val firstName: String
        get() = name.split(" ").firstOrNull() ?: ""
}

data class GroceryItem(
    val id: String,
    var name: String,
    var qty: String,
    var checked: Boolean,
    var addedBy: Int,
    var checkedBy: Int? = null,
    var removed: Boolean = false
)

data class ShoppingList(
    val id: String,
    var name: String,
    var updatedAt: String,
    var members: List<Member>,
    var items: List<GroceryItem>
) {
    val visibleItems: List<GroceryItem>
        get() = items.filter { !it.removed }

    val remainingCount: Int
        get() = items.count { !it.checked && !it.removed }

    val inviteCode: String
        get() {
            val raw = id.uppercase().replace("-", "")
            val padded = (raw + "FLOW2026").take(6)
            return padded.take(3) + "-" + padded.drop(3)
        }
}

data class ActivityNote(
    val id: String = UUID.randomUUID().toString(),
    val memberId: Int,
    val itemName: String,
    val at: Long = System.currentTimeMillis()
)
