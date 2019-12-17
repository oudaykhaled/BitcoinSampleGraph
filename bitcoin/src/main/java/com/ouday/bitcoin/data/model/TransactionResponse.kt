package com.ouday.bitcoin.data.model

data class TransactionResponse(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<Value>
)