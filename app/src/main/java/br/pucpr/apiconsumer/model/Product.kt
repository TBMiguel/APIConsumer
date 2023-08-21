package br.pucpr.apiconsumer.model

data class Product(
    val id: Long = 0L,
    var title: String,
    var price: Float,
    var description: String,
    var category: String,
)
