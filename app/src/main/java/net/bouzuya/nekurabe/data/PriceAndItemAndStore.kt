package net.bouzuya.nekurabe.data

import androidx.room.Embedded
import androidx.room.Relation

data class PriceAndItemAndStore(
    @Embedded val price: Price,

    @Relation(
        parentColumn = "itemId",
        entityColumn = "id"
    )
    val item: Item,

    @Relation(
        parentColumn = "storeId",
        entityColumn = "id"
    )
    val store: Store
) {
    val unitPrice
        get(): String = String.format("%.2f", price.price.toFloat() / price.amount.toFloat())
}
