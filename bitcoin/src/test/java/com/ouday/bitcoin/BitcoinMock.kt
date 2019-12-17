package com.ouday.bitcoin

import com.ouday.bitcoin.data.model.TransactionResponse
import com.ouday.bitcoin.data.model.Value

object BitcoinMock {

    val response: TransactionResponse

    init {

        val val1 = Value(1573574880, 3.7913111111110975)
        val val2 = Value(1573576920, 3.788588888888878)
        val values = ArrayList<Value>()
        values.add(val1)
        values.add(val2)

        response = TransactionResponse(
            "The number of Bitcoin transactions added to the mempool per second.",
            "Transaction Rate",
            "minute",
            "ok",
            "Transactions Per Second",
            values
        )

    }


}