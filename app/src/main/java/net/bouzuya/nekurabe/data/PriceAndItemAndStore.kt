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
    val unitPriceAsDouble
        get(): Double = price.price.toDouble() / price.amount.toDouble()

    val unitPrice
        get(): String = String.format("%.2f", unitPriceAsDouble)
}
